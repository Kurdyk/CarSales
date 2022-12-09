package Content.DataBase;

import Content.Clients.Client;
import Content.Clients.Company;
import Content.Clients.Particular;
import Content.Order;
import Content.Vehicles.Car;
import Content.Vehicles.Scooter;
import Content.Vehicles.Vehicle;
import com.mysql.cj.jdbc.exceptions.NotUpdatable;

import java.util.ArrayList;
import java.util.Objects;
import java.sql.*;


/**
 * The type Db connector.
 */
public class DBConnector {

    private static DBConnector instance = null;
    private Connection connection = null;
    private static String DB_URL;
    private static String USER;
    private static String PASS;

    /**
     * Sets db url.
     *
     * @param newURL the new url
     */
    public static void setDB_URL(String newURL) {
        DB_URL = newURL;
        instance = null;
    }

    /**
     * Sets user.
     *
     * @param newUser the new user
     */
    public static void setUSER(String newUser) {
        USER = newUser;
        instance = null;
    }

    /**
     * Sets password.
     *
     * @param newPass the new pass
     */
    public static void setPASS(String newPass) {
        PASS = newPass;
        instance = null;
    }

    /**
     * Private constructor for instantiation
     */
    private DBConnector() throws SQLException{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Instance getter
     *
     * @return the only instance of the class
     * @throws SQLException the sql exception
     */
    public static DBConnector getInstance() throws SQLException {
        if (DBConnector.instance == null) {
            DBConnector.instance = new DBConnector();
        }
        return DBConnector.instance;
    }

/// SELECT METHODS
    /**
     * Gets all cars.
     *
     * @return the all cars
     * @throws SQLException the sql exception
     */
    public ArrayList<Car> getAllCars() throws SQLException {
        String query = "SELECT * FROM Cars WHERE NOT Sold;";
        ArrayList<Car> result = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Car currentCar = new Car(
                resultSet.getString("Brand"),
                resultSet.getString("licence_plate"),
                resultSet.getLong("price"),
                resultSet.getDate("EntryDate"),
                resultSet.getString("OriginCountry"),
                resultSet.getString("Model"),
                resultSet.getLong("id"));
            result.add(currentCar);
        }
        return result;
    }

    /**
     * Gets all scooters.
     *
     * @return the all scooters
     * @throws SQLException the sql exception
     */
    public ArrayList<Scooter> getAllScooters() throws SQLException {
        String query = "SELECT * FROM Scooters WHERE NOT Sold;";
        ArrayList<Scooter> result = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Scooter currentScooter = new Scooter(
                resultSet.getString("Brand"),
                resultSet.getString("licence_plate"),
                resultSet.getLong("price"),
                resultSet.getDate("EntryDate"),
                resultSet.getString("OriginCountry"),
                resultSet.getString("Model"),
                resultSet.getLong("id"));
            result.add(currentScooter);
        }
        return result;
    }

    /**
     * Gets all particular.
     *
     * @return the all particular
     * @throws SQLException the sql exception
     */
    public ArrayList<Particular> getAllParticular() throws SQLException {
        String query = "SELECT * FROM Particular;";
        ArrayList<Particular> result = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Particular currentParticular = new Particular(
                resultSet.getString("Name"),
                resultSet.getString("Address"),
                resultSet.getInt("nbOrder"),
                resultSet.getLong("id"));
            result.add(currentParticular);
        }
        return result;
    }

    /**
     * Gets all company.
     *
     * @return the all company
     * @throws SQLException the sql exception
     */
    public ArrayList<Company> getAllCompany() throws SQLException {
        String query = "SELECT * FROM Company;";
        ArrayList<Company> result = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Company currentCompany = new Company(
                resultSet.getString("Name"),
                resultSet.getString("Address"),
                resultSet.getInt("nbOrder"),
                resultSet.getLong("id"),
                resultSet.getString("Siret"));
            result.add(currentCompany);
        }
        return result;
    }

    /**
     * Gets client by id.
     *
     * @param clientId the client id
     * @return the client by id
     * @throws SQLException the sql exception
     */
    public Client getClientById(long clientId) throws SQLException {
        String query = "SELECT * FROM Particular WHERE id = " + clientId + ";";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return new Particular(
                    resultSet.getString("Name"),
                    resultSet.getString("Address"),
                    resultSet.getInt("nbOrder"),
                    resultSet.getLong("id"));
        }
        query = "SELECT * FROM Company WHERE id = " + clientId + ";";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return new Company(
                    resultSet.getString("Name"),
                    resultSet.getString("Address"),
                    resultSet.getInt("nbOrder"),
                    resultSet.getLong("id"),
                    resultSet.getString("Siret"));
        }
        return null;
    }

    /**
     * Gets vehicle by id.
     *
     * @param vehicleId the vehicle id
     * @return the vehicle by id
     * @throws SQLException the sql exception
     */
    public Vehicle getVehicleById(long vehicleId) throws SQLException {
        String query = "SELECT * FROM Cars WHERE id = " + vehicleId + ";";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return new Car(
                    resultSet.getString("Brand"),
                    resultSet.getString("licence_plate"),
                    resultSet.getLong("price"),
                    resultSet.getDate("EntryDate"),
                    resultSet.getString("OriginCountry"),
                    resultSet.getString("Model"),
                    resultSet.getLong("id"));
        }
        query = "SELECT * FROM Scooters WHERE id = " + vehicleId + ";";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return  new Scooter(
                    resultSet.getString("Brand"),
                    resultSet.getString("licence_plate"),
                    resultSet.getLong("price"),
                    resultSet.getDate("EntryDate"),
                    resultSet.getString("OriginCountry"),
                    resultSet.getString("Model"),
                    resultSet.getLong("id"));
        }
        return null;
    }

    /**
     * Gets next available vehicle id.
     *
     * @return the next available vehicle id
     * @throws SQLException the sql exception
     */
    public long getNextAvailableVehicleId() throws SQLException {
        String queryCars = "SELECT MAX(id) as 'id' FROM Cars;";
        String queryScooter = "SELECT MAX(id) 'id' FROM Scooters;";
        return queryId(queryCars, queryScooter);
    }

    /**
     * Gets next available client id.
     *
     * @return the next available client id
     * @throws SQLException the sql exception
     */
    public long getNextAvailableClientId() throws SQLException {
        String queryParticular = "SELECT MAX(id) as 'id' FROM Particular;";
        String queryCompany = "SELECT MAX(id) as 'id' FROM Company;";
        return queryId(queryParticular, queryCompany);
    }

    private long queryId(String query1, String query2) throws SQLException {
        long result = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query1);
        if (resultSet.next()) {
            result = resultSet.getLong("id");
        }
        resultSet = statement.executeQuery(query2);
        if (resultSet.next()) {
            result = Math.max(resultSet.getLong("id"), result);
        }
        return result + 1;
    }

    /**
     * Query tax rate double.
     *
     * @param countryCode the country code
     * @return the double
     * @throws SQLException         the sql exception
     * @throws NullPointerException the null pointer exception
     */
    public double queryTaxRate(String countryCode) throws SQLException, NullPointerException {
        String query = "SELECT Taxe_rate FROM CountryTaxes WHERE Country_Code = '" + countryCode + "';";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getDouble("Taxe_rate");
        }
        throw new NullPointerException();
    }

    /**
     * Query orders array list.
     *
     * @param client the client
     * @return the array list
     * @throws SQLException the sql exception
     */
    public ArrayList<Order> queryOrders(Client client) throws SQLException {
        ArrayList<Order> result = new ArrayList<>();
        String query = "SELECT * FROM Sales WHERE Buyer = " + client.getId() + ";";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            Order order = new Order(this.getClientById(resultSet.getLong("Buyer")),
                                    this.getVehicleById(resultSet.getLong("BroughtVehicle")),
                                    resultSet.getBoolean("Payed"),
                                    resultSet.getString("Status")
                    );
            result.add(order);
        }
        return result;
    }

    /**
     * Gets number of order.
     *
     * @param client the client
     * @return the number of order
     * @throws SQLException the sql exception
     */
    public long getNumberOfOrder(Client client) throws SQLException {
        String query = "SELECT COUNT(*) AS COUNT FROM Sales WHERE Buyer = " + client.getId() + ";";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getLong("count");
        } else {
            throw new SQLException("NOT FOUND");
        }
    }

/// INSERT METHODS
    /**
     * Add vehicle.
     *
     * @param vehicle the vehicle
     * @throws SQLException the sql exception
     */
    public void addVehicle(Vehicle vehicle) throws SQLException {
        String tableName = (vehicle instanceof Car)? "Cars" : "Scooters";
        String query = "INSERT INTO " + tableName + " (id, licence_plate, price, EntryDate, Brand, OriginCountry, Model, Sold) " +
                "VALUE (" + vehicle.toSQLFormat() + ", FALSE) ;";
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
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    /**
     * Add client.
     *
     * @param client the client
     * @throws SQLException the sql exception
     */
    public void addClient(Client client) throws SQLException {
        if (client instanceof Particular) {
            this.addParticular((Particular) client);
        } else { // Company
            this.addCompany((Company) client);
        }
    }

    /**
     * Add order.
     *
     * @param order the order
     * @throws SQLException the sql exception
     */
    public void addOrder(Order order) throws SQLException {
        String query = "INSERT INTO Sales (Buyer, BroughtVehicle, Status, Payed) VALUE (" + order.toSQLFormat() + ");";
        System.out.println(query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    /**
     * Delete from table via id.
     *
     * @param id        the id
     * @param tableName the table name
     * @throws SQLException the sql exception
     */
/// DELETE METHODS
    public void deleteFromTableViaId(long id, String tableName) throws SQLException {
        String query = "DELETE FROM " + tableName + " WHERE id = " + id + ";";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    /**
     * Delete all.
     *
     * @throws SQLException the sql exception
     */
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

/// SET METHODS
    /**
     * Sets to sold.
     *
     * @param vehicle the vehicle
     * @throws SQLException the sql exception
     */
    public void setToSold(Vehicle vehicle) throws SQLException {
        String query;
        if (vehicle instanceof Car) {
            query = "UPDATE Cars SET Sold= TRUE WHERE id = " + vehicle.getId() + ";";
        } else {
            query = "UPDATE Scooters SET Sold= TRUE WHERE id = " + vehicle.getId() + ";";
        }
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    /**
     * Increase order status.
     *
     * @param order the order
     * @throws SQLException the sql exception
     */
    public void increaseOrderStatus(Order order) throws SQLException {
        String fill;
        switch (order.getCurrentStatus()) {
            case OUTGOING -> fill = "'VALIDATED'";
            case VALIDATED -> fill = "'DELIVERED'";
            default -> {return;}
        }
        String query = "UPDATE Sales SET Status= " + fill + " WHERE Buyer= " + order.getClient().getId() +
                " AND BroughtVehicle= " + order.getVehicle().getId() + ";";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public void setToPay(Order order) throws SQLException {
        if (order.isPayed()) {
            return;
        }
        String query = "UPDATE Sales SET Payed=TRUE WHERE Buyer= " + order.getClient().getId() +
                " AND BroughtVehicle= " + order.getVehicle().getId() + ";";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
}
