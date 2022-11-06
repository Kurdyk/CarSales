module Applications.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires itextpdf;
    requires java.sql;
    requires mysql.connector.java;

    opens Applications.VehiclesApp to javafx.fxml;
    opens Applications.ClientApp to javafx.fxml;
    opens Applications.OrderHistoryApp to javafx.fxml;

    exports Applications.Connexion;
    opens Applications.Connexion to javafx.fxml;
}