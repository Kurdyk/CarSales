package Content.Vehicles;

import java.util.Date;

public class Scooter extends Vehicle {

    public Scooter(String brand, String licencePlate, long value, Date date, String originCountry, String model) {
        super(brand, licencePlate, value, date, originCountry, model);
    }

    public Scooter(String brand, String licencePlate, long value, Date date, String originCountry, String model, long id) {
        super(brand, licencePlate, value, date, originCountry, model, id);
    }

    @Override
    public String toString() {
        return "Scooter : " + this.brand + ", " + this.model;
    }
}
