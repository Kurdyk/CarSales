package Applications.VehiclesApp;

import Content.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class VehiclesController {

    @FXML
    private ListView<Vehicle> vehicleListView;

    @FXML
    private void onDetailButtonClick() {
        System.out.println("Detail");
        vehicleListView.getItems().add(new Vehicle("a", "b", 10));
    }

}
