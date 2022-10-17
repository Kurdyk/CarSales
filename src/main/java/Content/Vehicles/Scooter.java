package Content.Vehicles;

import java.util.Date;

public class Scooter extends Vehicle {

    public Scooter(String brand, String licencePlate, long value, Date date) {
        super(brand, licencePlate, value, date);
    }

    @Override
    public String toString() {
        return "Scooter{" +
                "brand='" + brand + '\'' +
                ", licencePlate='" + licencePlate + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
