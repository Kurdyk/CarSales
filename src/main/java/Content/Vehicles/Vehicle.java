package Content.Vehicles;

import Applications.ErrorApp.ErrorApp;
import Content.DataBase.DBConnector;
import javafx.css.SizeUnits;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Date;

/**
 * The type Vehicle.
 */
public class Vehicle {

    private static long idClass = 0;

    /**
     * The Brand.
     */
    protected final String brand;
    /**
     * The Licence plate.
     */
    protected final String licencePlate;
    /**
     * The Value.
     */
    protected final long value;
    /**
     * The Date.
     */
    protected final Date date; // Since when is the vehicle available for sale
    /**
     * The Origin country.
     */
    protected final String originCountry;
    /**
     * The Model.
     */
    protected final String model;
    /**
     * The Id.
     */
    protected final long id;

    /**
     * A constructor made to create a vehicle and import it in the database
     *
     * @param brand         the brand of the vehicle
     * @param licencePlate  the licence plate of the vehicle
     * @param value         the original value of the vehicle
     * @param date          the date when the vehicle is up for sale (used to devaluate it if needed)
     * @param originCountry the country where the car is stored
     * @param model         the model of the vehicle
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
     *
     * @param brand         the brand of the vehicle
     * @param licencePlate  the licence plate of the vehicle
     * @param value         the original value of the vehicle
     * @param date          the date when the vehicle is up for sale (used to devaluate it if needed)
     * @param originCountry the country where the car is stored
     * @param model         the model of the vehicle
     * @param id            the id in the database of the vehicle
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

    /**
     * Gets discount value.
     *
     * @return the discount value
     */
    public long getDiscountValue() {
        Date currentDate = new Date();
        long yearDiff = currentDate.getYear() - this.date.getYear();
        long monthDiff = Math.max(currentDate.getMonth() - this.date.getMonth(), 0) + 12 * yearDiff;
        return Math.max(this.value / 2, this.value - (long) (this.value * (2 * monthDiff/ 100.0)));
    }

    /**
     * To sql format string.
     *
     * @return the string
     */
    public String toSQLFormat() {
        java.sql.Date sqlDate = new java.sql.Date(this.date.getTime());
        String dateForInsert = "STR_TO_DATE('" + sqlDate + "','%Y-%m-%d')";
        return this.id + ", '" + this.licencePlate + "', " + this.value + ", " + dateForInsert + ", '" + this.brand
                + "', '" + this.getOriginCountry() + "', '" + this.model + "'";
    }

    /**
     * Gets brand.
     *
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Gets licence plate.
     *
     * @return the licence plate
     */
    public String getLicencePlate() {
        return licencePlate;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public long getValue() {
        return value;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets origin country.
     *
     * @return the origin country
     */
    public String getOriginCountry() {
        return originCountry;
    }

    /**
     * Gets model.
     *
     * @return the model
     */
    public String getModel() {
        System.out.println("model = " + model);
        return model;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

}
