package com.police.policesystem.Controllers;

import com.police.policesystem.Models.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;

public class CaseReportAController implements Initializable {

    @FXML
    public TextField a1;
    @FXML
    public TextArea a9;
    @FXML
    public TextField a2;
    @FXML
    public TextField a3;
    @FXML
    public TextField a4;
    @FXML
    public TextField dateReported;
    @FXML
    public TextField a5;
    @FXML
    public TextField a6;
    @FXML
    public TextField a7;
    @FXML
    public TextField a8;
    @FXML
    public TextField caseRefNo;
    @FXML
    public Button saveAButton;
    @FXML
    public Button printAButton;
    Connection connection = null;
    PreparedStatement pst = null;

    public void Add() {
        connection = DatabaseConnection.ConnectDb();
        String sql = "INSERT INTO cases(a1, a2, caseRefNo, a3, a4, dateReported, a5, a6, a7, a8,a9) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, a1.getText());
            pst.setString(2, a2.getText());
            pst.setString(3, caseRefNo.getText());
            pst.setString(4, a3.getText());
            pst.setString(5, a4.toString());
            pst.setString(6, dateReported.getText());
            pst.setString(7, a5.getText());
            pst.setString(8, a6.getText());
            pst.setString(9, a7.getText());
            pst.setString(10, a8.getText());
            pst.setString(11, a9.getText());
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add");
            alert.setHeaderText(null);
            alert.setContentText("Police Abstract Added Successfully!");
            alert.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException("Cannot Add", e);
        }
    }




    public void generatePDFReport() throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/CaseReporta.fxml"));
        Parent filledFormView = loader.load();

        // Access the controller of the filled form view
        FilledFormController filledFormController = loader.getController();

        // Set the values from the database on the filled form
        connection = DatabaseConnection.ConnectDb();
        String sql = "SELECT * FROM cases";
        ResultSet rs = connection.createStatement().executeQuery(sql);

        while (rs.next()) {
            filledFormController.setFieldValues(rs.getString("a1"), rs.getString("a2"), rs.getString("caseRefNo"),
                    rs.getString("a3"), rs.getString("a4"), rs.getString("dateReported"), rs.getString("a5"), rs.getString("a6"),
                    rs.getString("a7"), rs.getString("a8"), rs.getString("a9"));
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
    public void initialize(URL location, ResourceBundle resources) {
        saveAButton.setOnAction(event -> {
            Add();
        });

        printAButton.setOnAction(event -> {
            try {
                generatePDFReport();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}
