package Applications.Connexion;

import Applications.ErrorApp.ErrorApp;
import Applications.MainApp.MainApp;
import Content.DataBase.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The type Connexion controller.
 */
public class ConnexionController {

    @FXML
    private TextField portField;
    @FXML
    private TextField IpField;

    @FXML
    private TextField idField;

    @FXML
    private PasswordField passwordField;

    /**
     * On connexion button click.
     */
    @FXML
    protected void onConnexionButtonClick() {
        String id = idField.getText();
        String password = passwordField.getText();
        String ip = IpField.getText();
        String port = portField.getText();
        DBConnector.setPASS(password);
        DBConnector.setDB_URL("jdbc:mysql://" + ip + ":" + port + "/CarSales");
        DBConnector.setUSER(id);
        try {
            DBConnector.getInstance();
        } catch (SQLException e) {
            ErrorApp errorApp = new ErrorApp("Connexion denied");
            Stage stage = new Stage();
            try {
                errorApp.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return;
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

    /**
     * On ok button click.
     *
     * @param event the event
     */
    @FXML
    protected void onOKButtonClick(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}