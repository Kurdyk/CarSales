package Test;

import Content.Clients.Company;
import Content.Clients.Particular;
import Content.DataBase.DBConnector;
import Content.Vehicles.Car;
import Content.Vehicles.Scooter;

import java.sql.SQLException;
import java.util.Date;

public class DBTester {

    public static void main(String[] args) {
        DBConnector dbConnector = null;
        DBConnector.setUSER("louis");
        DBConnector.setPASS("");
        DBConnector.setDB_URL("localhost");
        try {
            dbConnector = DBConnector.getInstance();
        } catch (SQLException e) {
            System.exit(1);
        }
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
            dbConnector.deleteFromTableViaId(1, "Cars");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Particular particular = new Particular("Jean Dupont", "Strasbourg");
            dbConnector.addClient(particular);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Company company = new Company("Jean Dupont", "Strasbourg", "21351321");
            dbConnector.addClient(company);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Particular particular = new Particular("Jean Dujardin", "Monaco");
            dbConnector.addClient(particular);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
