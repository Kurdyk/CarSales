package Test;

import Content.DataBase.DBConnector;

public class DBTester {

    public static void main(String[] args) {
        DBConnector dbConnector = DBConnector.getInstance();
        try {
            dbConnector.read_table("pute");
            System.out.println("DB read");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
