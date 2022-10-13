package Content;

public class Scooter extends Vehicle {

    public Scooter(String brand, String licencePlate, long value) {
        super(brand, licencePlate, value);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + this.brand + '\'' +
                ", licencePlate='" + this.licencePlate + '\'' +
                ", value=" + this.value +
                '}';
    }
}
