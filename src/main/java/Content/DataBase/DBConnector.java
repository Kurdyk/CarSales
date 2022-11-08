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


    /// SELECT METHODS
    public long getNextAvailableVehicleId() throws SQLException {
        String queryCars = "SELECT MAX(id) FROM Cars;";
        String queryScooter = "SELECT MAX(id) FROM Scooters;";
        return queryId(queryCars, queryScooter);
    }

    public long getNextAvailableClientId() throws SQLException {
        String queryParticular = "SELECT MAX(id) FROM Particular;";
        String queryCompany = "SELECT MAX(id) FROM Company;";
        return queryId(queryParticular, queryCompany);
    }

    private long queryId(String table1, String table2) throws SQLException {
        long result = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(table1);
        if (!resultSet.next()) {
            result = resultSet.getLong("id");
        }
        resultSet = statement.executeQuery(table2);
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
        String query = "INSERT INTO Company (id, Name, Address, nbOrder, Siret) VALUE (" + company.toSQLFormat() + ");";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    private void addParticular(Particular particular) throws SQLException {
        String query = "INSERT INTO Particular (id, Name, Address, nbOrder) VALUE (" + particular.toSQLFormat() + ");";
        System.out.println(query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public void addClient(Client client) throws SQLException {
        if (client instanceof Particular) {
            this.addParticular((Particular) client);
        } else { // Company
            this.addCompany((Company) client);
        }
    }

    /// DELETE METHODS
    public void deleteFromTableViaId(long id, String tableName) throws SQLException {
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
        query = "DELETE FROM Particular;";
        statement = connection.createStatement();
        statement.executeUpdate(query);
        query = "DELETE FROM Company;";
        statement = connection.createStatement();
        statement.executeUpdate(query);
    }

}
