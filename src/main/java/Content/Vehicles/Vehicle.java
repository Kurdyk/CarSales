package Content.Vehicles;

import Content.DataBase.DBConnector;

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

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", licencePlate='" + licencePlate + '\'' +
                ", value=" + value +
                ", date=" + date +
                ", originCountry='" + originCountry + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    public Vehicle(String brand, String licencePlate, long value, Date date, String originCountry, String model) {
        this.brand = brand;
        this.licencePlate = licencePlate;
        this.value = value;
        this.date = date;
        this.originCountry = originCountry;
        this.model = model;

        if (idClass == 0) {
            try {
                idClass = DBConnector.getInstance().getNextAvailableId();
            } catch (SQLException e) {
                // todo error screen
                e.printStackTrace();
            }
        }
        this.id = idClass++;
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
        return model;
    }

    public long getId() {
        return id;
    }

}
