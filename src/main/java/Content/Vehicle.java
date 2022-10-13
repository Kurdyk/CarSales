package Content;

public class Vehicle {

    protected final String brand;
    protected final String licencePlate;
    protected final long value;

    public Vehicle(String brand, String licencePlate, long value) {
        this.brand = brand;
        this.licencePlate = licencePlate;
        this.value = value;
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

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", licencePlate='" + licencePlate + '\'' +
                ", value=" + value +
                '}';
    }

}
