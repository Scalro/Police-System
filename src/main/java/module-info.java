module com.police.policesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
            requires net.synedra.validatorfx;
                requires org.kordamp.bootstrapfx.core;
            
    opens com.police.policesystem to javafx.fxml;
    exports com.police.policesystem;
    exports com.police.policesystem.Controllers;
    exports com.police.policesystem.Controllers.p3FormsControllers;
    exports com.police.policesystem.Controllers.Admin;
    exports com.police.policesystem.Views;
    exports com.police.policesystem.Models;
}