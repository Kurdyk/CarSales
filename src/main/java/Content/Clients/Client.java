package Content.Clients;

import Applications.ErrorApp.ErrorApp;
import Content.DataBase.DBConnector;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Client {

    private static long idClass = 0;

    protected final String name;
    protected final String address;
    protected final int nbOrder;
    protected final long id;

    public Client(String name, String address, int nbOrder, long id) {
        this.name = name;
        this.address = address;
        this.nbOrder = nbOrder;
        this.id = id;
    }

    public Client(String name, String address) {
        this.name = name;
        this.address = address;
        this.nbOrder = 0;
        if (idClass == 0) {
            try {
               idClass = DBConnector.getInstance().getNextAvailableClientId();
            } catch (SQLException e) {
                ErrorApp errorApp = new ErrorApp("SQL");
                Stage stage = new Stage();
                try {
                    errorApp.start(stage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.exit(1);
            }
        }
        this.id = idClass++;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public int getNbOrder() {
        return nbOrder;
    }

    public long getId() { return this.id; }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nbOrder=" + nbOrder +
                ", id=" + id +
                '}';
    }

}
