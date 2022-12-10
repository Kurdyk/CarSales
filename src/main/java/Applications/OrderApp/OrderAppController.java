package Applications.OrderApp;

import Applications.ErrorApp.ErrorApp;
import Applications.VehiclesApp.VehiclesApp;
import Content.Clients.Client;
import Content.Clients.Company;
import Content.Clients.Particular;
import Content.DataBase.DBConnector;
import Content.Order;
import Content.Vehicles.Vehicle;
import PdfHandler.Certificate;
import PdfHandler.CertificateBuilder;
import PdfHandler.Director;
import PdfHandler.PurchaseBuilder;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextInputControlSkin;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The type Order app controller.
 */
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

    /**
     * Instantiates a new Order app controller.
     *
     * @param toSell the to sell
     */
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
        // TODO : generate pdf
        // TODO : test
        Client buyer = this.clientSelector.getSelectionModel().getSelectedItem();
        if (buyer==null) return;
        Order order = new Order(buyer, this.toSell, this.paidCheck.isSelected());
        DBConnector dbConnector;
        File generatedPdfs = new File("generatedPdfs");
        if (!generatedPdfs.exists()){
            System.out.println("dir doesn't exist");
            generatedPdfs.mkdirs();
        }
        Director director = new Director(order);
        CertificateBuilder regBuilder = new CertificateBuilder();
        CertificateBuilder transfBuilder = new CertificateBuilder();
        PurchaseBuilder purchBuilder = new PurchaseBuilder();

        try {
            director.constructRegistrationCertificate(regBuilder,"generatedPdfs/RegistrationCertificate"+order.getClient().getId());
            director.constructTransferCertificate(transfBuilder, "generatedPdfs/TransferCertificate"+order.getClient().getId());
            director.constructPurchaseOrder(purchBuilder,"generatedPdfs/PurchaseOrder"+order.getVehicle().getId());
            /*Certificate certif = */
            regBuilder.build();
            transfBuilder.build();
            purchBuilder.build();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            dbConnector = DBConnector.getInstance();
            dbConnector.addOrder(order);
            dbConnector.setToSold(this.toSell);
        } catch (SQLException e) {
            e.printStackTrace();
            ErrorApp errorApp = new ErrorApp("Connexion error");
            Stage stage = new Stage();
            try {
                errorApp.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
            }
            return;
        }
        this.clientSelector.getScene().getWindow().hide();
    }

    private long finalPrice() throws EmptyCodeException, NullPointerException {
        String countryCode = this.countryTextField.getText();

        if (countryCode.equals("")) throw new EmptyCodeException("Empty code");

        DBConnector dbConnector;
        try {
            dbConnector = DBConnector.getInstance();
        } catch (SQLException e) {
            return -1;
        }
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
