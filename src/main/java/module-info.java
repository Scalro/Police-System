module com.police.policesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
            requires net.synedra.validatorfx;
                requires org.kordamp.bootstrapfx.core;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires javafx.swing;
    requires java.prefs;
    requires mysql.connector.j;

    opens com.police.policesystem to javafx.fxml;
    exports com.police.policesystem;
    exports com.police.policesystem.Controllers;
    exports com.police.policesystem.Controllers.p3FormsControllers;
    exports com.police.policesystem.Controllers.Admin;
    exports com.police.policesystem.Views;
    exports com.police.policesystem.Models;
}