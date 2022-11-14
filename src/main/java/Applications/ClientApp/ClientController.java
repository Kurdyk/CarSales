package Applications.ClientApp;

import Applications.ErrorApp.ErrorApp;
import Applications.MainApp.MainApp;
import Applications.OrderHistoryApp.OrderHistoryApp;
import Applications.VehiclesApp.VehiclesApp;
import Content.Clients.Client;
import Content.Clients.Company;
import Content.Clients.Particular;
import Content.DataBase.DBConnector;
import Content.Vehicles.Car;
import Content.Vehicles.Scooter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    /// Main client view
    @FXML
    private ListView<Client> clientListView;
    @FXML
    private Text name;
    @FXML
    private Text address;
    @FXML
    private Text nbOrders;
    @FXML
    private Text siret;
    @FXML
    private Button newClientButton;
    @FXML
    private Button returnButton;

    /// Client creation view
    private final Stage creationScreen = new Stage();
    @FXML
    private RadioButton companyRadioButton;
    @FXML
    private RadioButton particularRadioButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField siretField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    @FXML
    private void onDetailsClick() {
        System.out.println("Details");
        Client client = clientListView.getSelectionModel().getSelectedItem();
        if (client == null) {
            return;
        }
        name.setText(client.getName());
        address.setText(client.getAddress());
        nbOrders.setText(String.valueOf(client.getNbOrder()));
        siret.setText((client instanceof Company)? ((Company) client).getSiret() : "N/A");
    }

    @FXML
    private void onOrderHistoryClick() {
        System.out.println("Order History click");
        OrderHistoryApp orderHistoryApp = new OrderHistoryApp();
        Stage stage = new Stage();
        try {
            orderHistoryApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onNewClientClick() throws IOException {
        System.out.println("new Client");
        this.newClientButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("ClientCreatorView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        creationScreen.setTitle("Creation screen");
        creationScreen.setScene(scene);
        creationScreen.show();
    }

    @FXML
    private void onCancelButtonClick() {
        this.cancelButton.getScene().getWindow().hide();
        ClientApp clientApp = new ClientApp();
        Stage stage = new Stage();
        try {
            clientApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onConfirmButtonClick() {
        System.out.println("Confirm");
        String name;
        String address;
        String siret;
        try {
            name = this.nameField.getText();
            address = this.addressField.getText();
            siret = this.siretField.getText();
        } catch (Exception e) {
            error("Parsing");
            return;
        }
        try {
            if (this.companyRadioButton.isSelected()) {
                DBConnector.getInstance().addClient(new Company(name, address, siret));
            } else if (this.particularRadioButton.isSelected()) {
                DBConnector.getInstance().addClient(new Particular(name, address));
            } else {
                error("Select a radio button");
                return;
            }
        } catch (SQLException e) {
            error("SQL");
            e.printStackTrace();
            return;
        }
        this.confirmButton.getScene().getWindow().hide();
        ClientApp clientApp = new ClientApp();
        Stage stage = new Stage();
        try {
            clientApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateListView() {
        if (this.clientListView == null) {
            return;
        }
        System.out.println("update");
        ArrayList<Company> allCompany;
        ArrayList<Particular> allParticular;
        try {
            allCompany = DBConnector.getInstance().getAllCompany();
            allParticular = DBConnector.getInstance().getAllParticular();
        } catch (SQLException e) {
            ErrorApp errorApp = new ErrorApp("SQL");
            Stage stage = new Stage();
            try {
                errorApp.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return;
        }
        System.out.println(allCompany.size());
        System.out.println(allParticular.size());
        this.clientListView.getItems().removeAll();
        for (Client company : allCompany) {
            this.clientListView.getItems().add(company);
        }
        for (Client particular: allParticular) {
            this.clientListView.getItems().add(particular);
        }
        this.clientListView.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.updateListView();
    }

    private void error(String origin) {
        ErrorApp errorApp = new ErrorApp(origin);
        Stage stage = new Stage();
        try {
            errorApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void toMainMenu() {
        this.returnButton.getScene().getWindow().hide();
        MainApp mainApp = new MainApp();
        Stage stage = new Stage();
        try {
            mainApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
