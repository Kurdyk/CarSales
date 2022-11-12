package Applications.ClientApp;

import Applications.ErrorApp.ErrorApp;
import Applications.OrderHistoryApp.OrderHistoryApp;
import Content.Clients.Client;
import Content.Clients.Company;
import Content.Clients.Particular;
import Content.DataBase.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

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
    private void onNewClientClick() {
        Random r = new Random();
        if (r.nextBoolean()) {
            clientListView.getItems().add(new Company("company", "address", String.valueOf(r.nextInt())));
        } else {
            clientListView.getItems().add(new Particular("Henry", "address"));
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
}
