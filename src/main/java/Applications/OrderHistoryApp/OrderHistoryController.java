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
import javafx.scene.control.ListView;
import com.itextpdf.text.Document;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderHistoryController implements Initializable {

    private Client client;

    public OrderHistoryController(Client client) {
        this.client = client;
    }

    @FXML
    private ListView<Order> orderListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
