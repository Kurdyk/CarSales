package Applications.OrderApp;

import Applications.ErrorApp.ErrorApp;
import Applications.VehiclesApp.VehiclesApp;
import Content.Clients.Client;
import Content.Clients.Company;
import Content.Clients.Particular;
import Content.DataBase.DBConnector;
import Content.Vehicles.Vehicle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
    @FXML
    private Button calculateButton;
    @FXML
    private ChoiceBox<String> payingMethod;
    @FXML
    private CheckBox paidCheck;
    @FXML
    private ChoiceBox<Client> clientSelector;

    protected OrderAppController(Vehicle toSell) {
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

    private long finalPrice() throws EmptyCodeException, NullPointerException {
        String countryCode = this.countryTextField.getText();

        if (countryCode.equals("")) throw new EmptyCodeException("Empty code");

        DBConnector dbConnector = DBConnector.getInstance();
        double taxRate = 1.0;
        try {
            taxRate = dbConnector.queryTaxRate(countryCode);
        } catch (SQLException e) {
            ErrorApp errorApp = new ErrorApp("SQL");
            Stage stage = new Stage();
            try {
                errorApp.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return (this.toSell.getDiscountValue() + (long) (this.toSell.getDiscountValue() * taxRate));
    }

    @FXML
    private void onCalculateButtonClick() {

        try {
            this.finalPrice.setText(String.valueOf(this.finalPrice()));
            this.finalPrice.setFill(Color.BLACK);
        } catch (EmptyCodeException e) {
            this.finalPrice.setText("SET COUNTRY CODE");
            this.finalPrice.setFill(Color.RED);
        } catch (NullPointerException e) {
            this.finalPrice.setText("UNKNOWN COUNTRY CODE");
            this.finalPrice.setFill(Color.RED);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.vehicleText.setText(this.toSell.toString());
        this.originalPrice.setText(String.valueOf(this.toSell.getValue()));
        this.discountPrice.setText(String.valueOf(this.toSell.getDiscountValue()));

        this.payingMethod.getItems().add("Cash");
        this.payingMethod.getItems().add("Credit card");
        this.payingMethod.getItems().add("Check");

        ArrayList<Company> allCompanies = new ArrayList<>();
        ArrayList<Particular> allParticulars = new ArrayList<>();
        try {
            allCompanies = DBConnector.getInstance().getAllCompany();
            allParticulars = DBConnector.getInstance().getAllParticular();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Client client : allCompanies) {
            this.clientSelector.getItems().add(client);
        }
        for (Client client : allParticulars) {
            this.clientSelector.getItems().add(client);
        }
    }


}
