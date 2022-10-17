package Content.Vehicles;

import java.util.Date;

public class Vehicle {

    protected final String brand;
    protected final String licencePlate;
    protected final long value;
    protected final Date date; // Since when is the vehicle available for sale

    /* todo : to implement, one day or another
    protected Color color;
    protected int horsePower;
    protected double space;
    protected String model;
     */

    public Vehicle(String brand, String licencePlate, long value, Date date) {
        this.brand = brand;
        this.licencePlate = licencePlate;
        this.value = value;
        this.date = date;
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

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", licencePlate='" + licencePlate + '\'' +
                ", value=" + value +
                '}';
    }

}
