package com.police.policesystem.Controllers;

import com.police.policesystem.Models.DatabaseConnection;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CaseReportBController implements Initializable {
    @FXML
    public TextField b1;

    @FXML
    public TextField b2;

    @FXML
    public TextField b3;

    @FXML
    public TextField b4;

    @FXML
    public TextField b5;

    @FXML
    public TextField b6;

    @FXML
    public TextField b7;

    @FXML
    public TextField b8;

    @FXML
    public TextField b9;
    @FXML
    public Button saveBButton;

    @FXML
    public Button printButton;

    Connection connection = null;
    PreparedStatement pst = null;

    public void Add() {
        connection = DatabaseConnection.ConnectDb();
        String sql = "INSERT INTO caseB(b1,b2,b3,b4,b5,b6,b7,b8,b9) VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, b1.getText());
            pst.setString(2, b2.getText());
            pst.setString(3, b3.getText());
            pst.setString(4, b4.getText());
            pst.setString(5, b5.getText());
            pst.setString(6, b6.getText());
            pst.setString(7, b7.getText());
            pst.setString(8, b8.getText());
            pst.setString(9, b9.getText());
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add");
            alert.setHeaderText(null);
            alert.setContentText("CaseReportB Added Successfully!");
            alert.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException("Cannot Add", e);
        }
    }


    public void generatePDFReport() throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/CaseReportb.fxml"));
        Parent filledFormView = loader.load();

        // Access the controller of the filled form view
        FilledFormBController filledFormController = loader.getController();

        // Set the values from the database on the filled form
        connection = DatabaseConnection.ConnectDb();
        String sql = "SELECT * FROM caseB";
        ResultSet rs = connection.createStatement().executeQuery(sql);

        while (rs.next()) {
            filledFormController.setFieldValues(rs.getString("b1"), rs.getString("b2"), rs.getString("b3"),
                    rs.getString("b4"), rs.getString("b5"), rs.getString("b6"), rs.getString("b7"),
                    rs.getString("b8"), rs.getString("b9"));
        }

        // Display the filled form view
        Stage filledFormStage = new Stage();
        filledFormStage.setTitle("Filled Form");
        filledFormStage.setScene(new Scene(filledFormView));
        filledFormStage.show();

        // Capture the scene content as an image
        WritableImage snapshot = filledFormView.snapshot(new SnapshotParameters(), null);

        // Save the image as a file (you can adjust the file format and path as needed)
        File outputFile = new File("filled_form.png");
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
        ImageIO.write(bufferedImage, "png", outputFile);


        // Generate the PDF report
        try (PDDocument document = new PDDocument()) {
            // Create a new page in the PDF document
            PDPage page = new PDPage();
            document.addPage(page);

            // Load the image from the file
            PDImageXObject pdImage = PDImageXObject.createFromFileByContent(outputFile, document);

            // Calculate the width and height of the image to fit the page
            float pageWidth = page.getMediaBox().getWidth();
            float pageHeight = page.getMediaBox().getHeight();
            float imageWidth = pdImage.getWidth();
            float imageHeight = pdImage.getHeight();
            float scalingFactor = Math.min(pageWidth / imageWidth, pageHeight / imageHeight);
            float scaledWidth = imageWidth * scalingFactor;
            float scaledHeight = imageHeight * scalingFactor;

            // Draw the image on the page
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.drawImage(pdImage, (pageWidth - scaledWidth) / 2, (pageHeight - scaledHeight) / 2, scaledWidth, scaledHeight);
            }

            // Save the PDF file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                document.save(file);
                document.close();
                openPDF(file);
            }
        }
    }

    public void openPDF(File file) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveBButton.setOnAction(event -> Add());
        printButton.setOnAction(event -> {
            try {
                generatePDFReport();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }
}
