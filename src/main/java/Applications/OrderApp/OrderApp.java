package Applications.OrderApp;

import Applications.OrderHistoryApp.OrderHistoryApp;
import Content.Vehicles.Vehicle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OrderApp extends Application {

    protected Vehicle vehicleToSale;

    public OrderApp(Vehicle vehicleToSale) {
        this.vehicleToSale = vehicleToSale;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(OrderApp.class.getResource("OrderView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        OrderAppController orderAppController = fxmlLoader.getController();
        orderAppController.setToSell(vehicleToSale);
        stage.setTitle("A car vendor App");
        stage.setScene(scene);
        stage.show();
    }
}
