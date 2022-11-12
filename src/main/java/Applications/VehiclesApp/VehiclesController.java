package Applications.VehiclesApp;

import Applications.ErrorApp.ErrorApp;
import Applications.MainApp.MainApp;
import Content.DataBase.DBConnector;
import Content.Vehicles.Car;
import Content.Vehicles.Scooter;
import Content.Vehicles.Vehicle;
import javafx.css.SizeUnits;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VehiclesController implements Initializable {

    /// Main view
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
    private Text model;
    @FXML
    private Text date;
    @FXML
    private Button newVehicleButton;
    @FXML
    private Button returnButton;

    /// Creation View
    private final Stage creationScreen = new Stage();
    @FXML
    private RadioButton carRadioButton;
    @FXML
    private RadioButton scooterRadioButton;
    @FXML
    private TextField brandField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField licencePlateField;
    @FXML
    private TextField valueField;
    @FXML
    private TextField dateField = new TextField();
    @FXML
    private TextField originCountryField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);

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
        model.setText(current.getModel());
        type.setText((current instanceof Car)? "Car" : "Scooter");
        date.setText(current.getDate().toString());
    }

    @FXML
    private void onAddVehicleButtonClick() throws IOException {
        System.out.println("New vehicle");
        this.newVehicleButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(VehiclesApp.class.getResource("VehicleCreatorView.fxml"));
        this.dateField.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format)));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        creationScreen.setTitle("Creation screen");
        creationScreen.setScene(scene);
        creationScreen.show();
    }

    @FXML
    private void onConfirmButtonClick() {
        System.out.println("Confirm");
        String brand;
        String model;
        String originCountry;
        String licencePlate;
        long value;
        try {
            brand = this.brandField.getText();
            model = this.modelField.getText();
            originCountry = this.originCountryField.getText();
            licencePlate = this.licencePlateField.getText();
            value = Long.parseLong(this.valueField.getText());
        } catch (Exception e) {
            error("Parsing");
            return;
        }
        Date date;
        try {
            date = format.parse(this.dateField.getText());
        } catch (ParseException e) {
            error("Parsing, date format is dd/MM/yyyy");
            return;
        }
        try {
            if (this.scooterRadioButton.isSelected()) {
                DBConnector.getInstance().addVehicle(new Scooter(brand, licencePlate, value, date, originCountry, model));
            } else if (this.carRadioButton.isSelected()) {
                DBConnector.getInstance().addVehicle(new Car(brand, licencePlate, value, date, originCountry, model));
            } else {
                error("Select a radio button");
                return;
            }
        } catch (SQLException e) {
            error("SQL");
            e.printStackTrace();
            return;
        }
        this.confirmButton.getScene().getWindow().hide();
        VehiclesApp vehiclesApp = new VehiclesApp();
        Stage stage = new Stage();
        try {
            vehiclesApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCancelButtonClick() {
        this.cancelButton.getScene().getWindow().hide();
        VehiclesApp vehiclesApp = new VehiclesApp();
        Stage stage = new Stage();
        try {
            vehiclesApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateListView() {
        if (this.vehicleListView == null) {
            return;
        }
        System.out.println("update");
        ArrayList<Car> allCars;
        ArrayList<Scooter> allScooters;
        try {
            allCars = DBConnector.getInstance().getAllCars();
            allScooters = DBConnector.getInstance().getAllScooters();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(allCars.size());
        System.out.println(allScooters.size());
        this.vehicleListView.getItems().removeAll();
        for (Vehicle car : allCars) {
            this.vehicleListView.getItems().add(car);
        }
        for (Vehicle scooter : allScooters) {
            this.vehicleListView.getItems().add(scooter);
        }
        this.vehicleListView.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.updateListView();
    }

    private void error(String origin) {
        ErrorApp errorApp = new ErrorApp(origin);
        Stage stage = new Stage();
        try {
            errorApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void toMainMenu() {
        this.returnButton.getScene().getWindow().hide();
        MainApp mainApp = new MainApp();
        Stage stage = new Stage();
        try {
            mainApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
