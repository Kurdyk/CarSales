package Applications.OrderHistoryApp;

import Applications.ErrorApp.ErrorApp;
import Content.Clients.Client;
import Content.DataBase.DBConnector;
import Content.Order;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import com.itextpdf.text.Document;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The type Order history controller.
 */
public class OrderHistoryController implements Initializable {

    private Client client;

    /**
     * Instantiates a new Order history controller.
     *
     * @param client the client
     */
    public OrderHistoryController(Client client) {
        this.client = client;
    }

    @FXML
    private ListView<Order> orderListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       this.updateList();
    }

    @FXML
    private void onSetToPayedButtonClick() {
        if (this.orderListView.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        try {
            DBConnector.getInstance().setToPay(this.orderListView.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            ErrorApp errorApp = new ErrorApp("Connexion denied");
            Stage stage = new Stage();
            try {
                errorApp.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        }
        this.updateList();
    }

    @FXML
    private void onIncrementStatusButtonClick() {
        if (this.orderListView.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        try {
            DBConnector.getInstance().increaseOrderStatus(this.orderListView.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            ErrorApp errorApp = new ErrorApp("Connexion denied");
            Stage stage = new Stage();
            try {
                errorApp.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        }
        this.updateList();
    }

    private void updateList() {
        this.orderListView.getItems().clear();
        this.orderListView.getItems().removeAll();
        try {
            this.orderListView.getItems().addAll(DBConnector.getInstance().queryOrders(this.client));
        } catch (SQLException e) {
            ErrorApp errorApp = new ErrorApp("Connexion denied");
            Stage stage = new Stage();
            try {
                errorApp.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        }
    }
}
