module Applications.project {
    requires javafx.controls;
    requires javafx.fxml;


    opens Applications.MainApp to javafx.fxml;
    opens Applications.VehiclesApp to javafx.fxml;
    opens Applications.ClientApp to javafx.fxml;
    exports Applications.Connexion;
    opens Applications.Connexion to javafx.fxml;
}