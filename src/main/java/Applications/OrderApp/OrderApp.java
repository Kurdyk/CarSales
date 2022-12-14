package Applications.OrderApp;

import Content.Vehicles.Vehicle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The type Order app.
 */
public class OrderApp extends Application {

    /**
     * The Vehicle to sale.
     */
    protected Vehicle vehicleToSale;

    /**
     * Instantiates a new Order app.
     *
     * @param vehicleToSale the vehicle to sale
     */
    public OrderApp(Vehicle vehicleToSale) {
        this.vehicleToSale = vehicleToSale;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(OrderApp.class.getResource("OrderView.fxml"));
        OrderAppController orderAppController = new OrderAppController(vehicleToSale);
        fxmlLoader.setController(orderAppController);
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("A car vendor App");
        stage.setScene(scene);
        stage.show();
    }
}
