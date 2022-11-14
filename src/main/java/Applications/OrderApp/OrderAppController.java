package Applications.OrderApp;

import Applications.VehiclesApp.VehiclesApp;
import Content.Vehicles.Vehicle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class OrderAppController implements Initializable {

    private Vehicle toSell;

    @FXML
    private Text vehicleText;
    @FXML
    private TextField countryTextField;
    @FXML
    private Text originalPrice;
    @FXML
    private Text discountPrice;
    @FXML
    private Text finalPrice;
    @FXML
    private Button cancelButton;
    @FXML
    private Button confirmButton;

    protected void setToSell(Vehicle toSell) {
        this.toSell = toSell;
    }

    @FXML
    private void onCancelButtonClick() {
        this.cancelButton.getScene().getWindow().hide();
        VehiclesApp vehiclesApp = new VehiclesApp();
        Stage stage = new Stage();
        try {
            vehiclesApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    private void onConfirmButtonClick() {
        // TODO : generate pdf and set Vehicle to sold in BD
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.vehicleText.setText(this.toSell.toString());
//        this.originalPrice.setText(String.valueOf(this.toSell.getValue()));
//        this.discountPrice.setText(String.valueOf(this.toSell.getValue()));
    }
}
