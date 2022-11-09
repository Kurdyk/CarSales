package Applications.VehiclesApp;

import Content.DataBase.DBConnector;
import Content.Vehicles.Car;
import Content.Vehicles.Scooter;
import Content.Vehicles.Vehicle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VehiclesController {

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
    private Text Model;
    @FXML
    private Text date;

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
        type.setText((current instanceof Car)? "Car" : "Scooter");
        date.setText(current.getDate().toString());
    }

    @FXML
    private void onAddVehicleButtonClick() throws IOException {
        System.out.println("New vehicle");
        this.dateField.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format)));
        FXMLLoader fxmlLoader = new FXMLLoader(VehiclesApp.class.getResource("VehicleCreatorView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        creationScreen.setTitle("Creation screen");
        creationScreen.setScene(scene);
        creationScreen.show();
    }

    @FXML
    private void onConfirmButtonClick() {
        System.out.println("Confirm");
        String brand = this.brandField.getText();
        String model = this.modelField.getText();
        String originCountry = this.originCountryField.getText();
        String licencePlate = this.licencePlateField.getText();
        long value = Long.parseLong(this.valueField.getText());
        Date date = null;
        try {
            date = format.parse(this.dateField.getText());
        } catch (ParseException e) {
            // todo error screen
            e.printStackTrace();
            return;
        }
        try {
            if (this.scooterRadioButton.isSelected()) {
                DBConnector.getInstance().addVehicle(new Scooter(brand, licencePlate, value, date, originCountry, model));
            } else if (this.carRadioButton.isSelected()) {
                DBConnector.getInstance().addVehicle(new Car(brand, licencePlate, value, date, originCountry, model));
            } else {
                // todo error screen
            }
        } catch (SQLException e) {
            // todo error screen
            e.printStackTrace();
        }
        this.confirmButton.getScene().getWindow().hide();
    }

    @FXML
    private void onCancelButtonClick() {
        this.cancelButton.getScene().getWindow().hide();
    }
}
