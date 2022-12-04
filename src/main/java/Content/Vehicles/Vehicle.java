package Content.Vehicles;

import Applications.ErrorApp.ErrorApp;
import Content.DataBase.DBConnector;
import javafx.css.SizeUnits;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Date;

public class Vehicle {

    private static long idClass = 0;

    protected final String brand;
    protected final String licencePlate;
    protected final long value;
    protected final Date date; // Since when is the vehicle available for sale
    protected final String originCountry;
    protected final String model;
    protected final long id;

    /**
     * A constructor made to create a vehicle and import it in the database
     * @param brand the brand of the vehicle
     * @param licencePlate the licence plate of the vehicle
     * @param value the original value of the vehicle
     * @param date the date when the vehicle is up for sale (used to devaluate it if needed)
     * @param originCountry the country where the car is stored
     * @param model the model of the vehicle
     */
    public Vehicle(String brand, String licencePlate, long value, Date date, String originCountry, String model) {
        this.brand = brand;
        this.licencePlate = licencePlate;
        this.value = value;
        this.date = date;
        this.originCountry = originCountry;
        this.model = model;
        if (idClass == 0) {
            try {
                idClass = DBConnector.getInstance().getNextAvailableVehicleId();
            } catch (SQLException e) {
                e.printStackTrace();
                ErrorApp errorApp = new ErrorApp("SQL reading");
                Stage stage = new Stage();
                try {
                    errorApp.start(stage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        }
        this.id = idClass++;
    }

    /**
     * A constructor made to create a Vehicle object from the database
     * @param brand the brand of the vehicle
     * @param licencePlate the licence plate of the vehicle
     * @param value the original value of the vehicle
     * @param date the date when the vehicle is up for sale (used to devaluate it if needed)
     * @param originCountry the country where the car is stored
     * @param model the model of the vehicle
     * @param id the id in the database of the vehicle
     */
    public Vehicle(String brand, String licencePlate, long value, Date date, String originCountry, String model, long id) {
        this.brand = brand;
        this.licencePlate = licencePlate;
        this.value = value;
        this.date = date;
        this.originCountry = originCountry;
        this.model = model;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", licencePlate='" + licencePlate + '\'' +
                ", value=" + value +
                ", date=" + date +
                ", originCountry='" + originCountry + '\'' +
                ", model='" + model + '\'' +
                ", id=" + id +
                '}';
    }

    public long getDiscountValue() {
        Date currentDate = new Date();
        long yearDiff = currentDate.getYear() - this.date.getYear();
        long monthDiff = Math.max(currentDate.getMonth() - this.date.getMonth(), 0) + 12 * yearDiff;
        return Math.max(this.value / 2, this.value - (long) (this.value * (2 * monthDiff/ 100.0)));
    }

    public String toSQLFormat() {
        java.sql.Date sqlDate = new java.sql.Date(this.date.getTime());
        String dateForInsert = "STR_TO_DATE('" + sqlDate + "','%Y-%m-%d')";
        return this.id + ", '" + this.licencePlate + "', " + this.value + ", " + dateForInsert + ", '" + this.brand
                + "', '" + this.getOriginCountry() + "', '" + this.model + "'";
    }

    public String getBrand() {
        return brand;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public long getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public String getModel() {
        System.out.println("model = " + model);
        return model;
    }

    public long getId() {
        return id;
    }

}
