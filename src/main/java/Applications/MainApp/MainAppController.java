package Applications.MainApp;

import Applications.VehiclesApp.VehiclesApp;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainAppController {

    @FXML
    private ImageView cars;

    @FXML
    private ImageView clients;

    @FXML
    private ImageView newOrder;

    @FXML
    private ImageView oldOrders;

    @FXML
    private void onCarsClick() {
        System.out.println("Cars click");
        VehiclesApp vehiclesApp = new VehiclesApp();
        Stage stage = new Stage();
        try {
            vehiclesApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClientsClicked() {
        System.out.println("Client click");
    }

    @FXML
    private void onNewOrderClicked() {
        System.out.println("New order click");
    }

    @FXML
    private void onOldOrderClicked() {
        System.out.println("Old order click");
    }

}
