package Content.DataBase;

import java.util.Objects;
import java.sql.*;


public class DBConnector {

    public static DBConnector instance = null;
    private Connection connection = null;
//    private Statement statement = null;

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

    public static DBConnector getInstance() {
        return Objects.requireNonNullElseGet(instance, DBConnector::new);
    }

    public void read_table(String tableName) throws SQLException {

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

}
