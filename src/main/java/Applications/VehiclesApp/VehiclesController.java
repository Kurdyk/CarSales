package Applications.VehiclesApp;

import Content.Vehicles.Car;
import Content.Vehicles.Scooter;
import Content.Vehicles.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.Date;
import java.util.Random;

public class VehiclesController {

    @FXML
    private ListView<Vehicle> vehicleListView;

    @FXML
    private Text brand;

    @FXML
    private Text licencePlate;

    @FXML
    private Text value;

    @FXML
    private Text type;

    @FXML
    private Text Model;

    @FXML
    private Text date;

    @FXML
    private void onDetailButtonClick() {
        System.out.println("Detail");

        Vehicle current = vehicleListView.getSelectionModel().getSelectedItem();
        if (current == null) {
            return;
        }
        System.out.println(current);
        brand.setText(current.getBrand());
        licencePlate.setText(current.getLicencePlate());
        value.setText(String.valueOf(current.getValue()));
        type.setText((current instanceof Car)? "Car" : "Scooter");
        date.setText(current.getDate().toString());
    }

    @FXML
    private void onAddVehicleButtonClick() {
        System.out.println("New vehicle");
        Random r = new Random();
        if (r.nextBoolean()) {
            vehicleListView.getItems().add(new Scooter("a", "b", r.nextInt(100), new Date()));
        } else {
            vehicleListView.getItems().add(new Car("A", "B", r.nextInt(100), new Date()));
        }
    }

}
