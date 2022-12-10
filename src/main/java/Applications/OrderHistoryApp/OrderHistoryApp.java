package Applications.OrderHistoryApp;

import Applications.MainApp.MainApp;
import Applications.OrderApp.OrderAppController;
import Content.Clients.Client;
import Content.Order;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The type Order history app.
 */
public class OrderHistoryApp extends Application {

    private Client client;

    /**
     * Instantiates a new Order history app.
     *
     * @param client the client
     */
    public OrderHistoryApp(Client client) {
        this.client = client;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(OrderHistoryApp.class.getResource("OrderHistoryView.fxml"));
        OrderHistoryController orderHistoryController = new OrderHistoryController(this.client);
        fxmlLoader.setController(orderHistoryController);
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("A car vendor App");
        stage.setScene(scene);
        stage.show();
    }
}
