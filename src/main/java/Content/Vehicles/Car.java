package Content.Vehicles;

import java.util.Date;

public class Car extends Vehicle {

    public Car(String brand, String licencePlate, long value, Date date, String originCountry, String model) {
        super(brand, licencePlate, value, date, originCountry, model);
    }

    public Car(String brand, String licencePlate, long value, Date date, String originCountry, String model, long id) {
        super(brand, licencePlate, value, date, originCountry, model, id);
    }

    @Override
    public String toString() {
        return "Car : " + this.brand + ", " + this.model;
    }
}
