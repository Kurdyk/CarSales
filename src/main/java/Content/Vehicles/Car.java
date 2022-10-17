package Content.Vehicles;

import java.util.Date;

public class Car extends Vehicle {

    public Car(String brand, String licencePlate, long value, Date date) {
        super(brand, licencePlate, value, date);
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", licencePlate='" + licencePlate + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
