package Test;

import Content.DataBase.DBConnector;
import Content.Vehicles.Car;
import Content.Vehicles.Scooter;
import Content.Vehicles.Vehicle;

import java.sql.SQLException;
import java.util.Date;

public class DBTester {

    public static void main(String[] args) {
        DBConnector dbConnector = DBConnector.getInstance();
        try {
            dbConnector.deleteAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Car car = new Car("a", "b", 10, new Date(), "FR", "X");
            System.out.println(car.getId());
            DBConnector.getInstance().addVehicle(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Car car = new Car("a", "b", 10, new Date(), "FR", "X");
            System.out.println(car.getId());
            DBConnector.getInstance().addVehicle(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Scooter scooter = new Scooter("a", "b", 10, new Date(), "FR", "X");
            System.out.println(scooter.getId());
            DBConnector.getInstance().addVehicle(scooter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            dbConnector.deleteVehicle(1, "Cars");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
