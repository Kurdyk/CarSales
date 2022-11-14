package Applications.Connexion;

import Applications.MainApp.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ConnexionController {

    @FXML
    private TextField idField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onConnexionButtonClick() {
        String id = idField.getText();
        String password = passwordField.getText();
        System.out.println(id + password);
        if (id.equals("Test")) {
            System.out.println("reject");
            try {
                rejectConnexion();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MainApp mainApp = new MainApp();
        Stage stage = new Stage();
        try {
            mainApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rejectConnexion() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ConnexionApplication.class.getResource("RejectConnexionView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        stage.setTitle("A reject message");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onOKButtonClick(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}