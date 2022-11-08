package Content.Vehicles;

import java.util.Date;

public class Scooter extends Vehicle {

    public Scooter(String brand, String licencePlate, long value, Date date, String originCountry, String model) {
        super(brand, licencePlate, value, date, originCountry, model);
    }

    @Override
    public String toString() {
        return "Scooter{" +
                "brand='" + brand + '\'' +
                ", licencePlate='" + licencePlate + '\'' +
                ", value=" + value +
                ", date=" + date +
                ", originCountry='" + originCountry + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
