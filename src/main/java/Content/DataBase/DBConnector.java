package Content.DataBase;

import Content.Clients.Client;
import Content.Clients.Company;
import Content.Clients.Particular;
import Content.Vehicles.Car;
import Content.Vehicles.Vehicle;

import java.util.Objects;
import java.sql.*;


public class DBConnector {

    public static DBConnector instance = null;
    private Connection connection = null;

    /**
     * Private constructor for instantiation
     */
    private DBConnector() {
        String DB_URL = "jdbc:mysql://192.168.1.20:3306/CarSales";
        String USER = "louis";
        String PASS = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Instance getter
     * @return the only instance of the class
     */
    public static DBConnector getInstance() {
        return Objects.requireNonNullElseGet(instance, DBConnector::new);
    }

    /**
     * Mostly for test purpose
     * @throws SQLException
     */
    public void read_table() throws SQLException {

        String QUERY = "SELECT * from Cars";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        // Extract data from result set
        while (rs.next()) {
            // Retrieve by column name
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("licence = " + rs.getString("licence_plate"));
            System.out.println("price = " + rs.getDouble("price"));
        }
    }

    /// SELECT METHODS
    public long getNextAvailableId() throws SQLException {
        String queryCars = "SELECT MAX(id) FROM Cars;";
        String queryScooter = "SELECT MAX(id) FROM Scooters;";
        long result = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(queryCars);
        if (!resultSet.next()) {
            result = resultSet.getLong("id");
        }
        resultSet = statement.executeQuery(queryScooter);
        if (!resultSet.next()) {
            result = resultSet.getLong("id");
        }
        return result + 1;
    }

    /// INSERT METHODS
    public void addVehicle(Vehicle vehicle) throws SQLException {
        String tableName = (vehicle instanceof Car)? "Cars" : "Scooters";
        String query = "INSERT INTO " + tableName + " (id, licence_plate, price, EntryDate, Brand, OriginCountry, Model, Sold) " +
                "VALUE (" + vehicle.toSQLFormat() + ", FALSE) ;";
        System.out.println(query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    private void addCompany(Company company) throws SQLException {
        String query = "INSERT INTO Company (id, Name, Address, nbOrder, Siret) VALUE ";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public void addClient(Client client) throws SQLException {
    }

    /// DELETE METHODS
    public void deleteVehicle(Vehicle vehicle) throws SQLException {
        String tableName = (vehicle instanceof Car)? "Cars" : "Scooters";
        String query = "DELETE FROM " + tableName + " WHERE id = " + vehicle.getId();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public void deleteVehicle(long id, String tableName) throws SQLException {
        String query = "DELETE FROM " + tableName + " WHERE id = " + id + ";";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public void deleteAll() throws SQLException {
        String query = "DELETE FROM Cars;";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        query = "DELETE FROM Scooters;";
        statement = connection.createStatement();
        statement.executeUpdate(query);
    }

}
