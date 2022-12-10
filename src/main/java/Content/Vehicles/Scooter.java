package Content.Vehicles;

import java.util.Date;

/**
 * The type Scooter.
 */
public class Scooter extends Vehicle {

    /**
     * Instantiates a new Scooter.
     *
     * @param brand         the brand
     * @param licencePlate  the licence plate
     * @param value         the value
     * @param date          the date
     * @param originCountry the origin country
     * @param model         the model
     */
    public Scooter(String brand, String licencePlate, long value, Date date, String originCountry, String model) {
        super(brand, licencePlate, value, date, originCountry, model);
    }

    /**
     * Instantiates a new Scooter.
     *
     * @param brand         the brand
     * @param licencePlate  the licence plate
     * @param value         the value
     * @param date          the date
     * @param originCountry the origin country
     * @param model         the model
     * @param id            the id
     */
    public Scooter(String brand, String licencePlate, long value, Date date, String originCountry, String model, long id) {
        super(brand, licencePlate, value, date, originCountry, model, id);
    }

    @Override
    public String toString() {
        return "Scooter : " + this.brand + ", model " + this.model;
    }
}
