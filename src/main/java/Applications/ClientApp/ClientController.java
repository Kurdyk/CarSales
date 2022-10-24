package Applications.ClientApp;

import Applications.OrderHistoryApp.OrderHistoryApp;
import Applications.VehiclesApp.VehiclesApp;
import Content.Clients.Client;
import Content.Clients.Company;
import Content.Clients.Particular;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class ClientController {

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
}
