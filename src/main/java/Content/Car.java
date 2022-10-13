package Content;

public class Car extends Vehicle {

    public Car(String brand, String licencePlate, long value) {
        super(brand, licencePlate, value);
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + this.brand + '\'' +
                ", licencePlate='" + this.licencePlate + '\'' +
                ", value=" + this.value +
                '}';
    }
}
