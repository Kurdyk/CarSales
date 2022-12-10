package Content.Clients;

import Applications.ErrorApp.ErrorApp;
import Content.DataBase.DBConnector;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * The type Client.
 */
public class Client {

    private static long idClass = 0;

    /**
     * The Name.
     */
    protected final String name;
    /**
     * The Address.
     */
    protected final String address;
    /**
     * The Nb order.
     */
    protected final int nbOrder;
    /**
     * The Id.
     */
    protected final long id;

    /**
     * Instantiates a new Client.
     *
     * @param name    the name
     * @param address the address
     * @param nbOrder the nb order
     * @param id      the id
     */
    public Client(String name, String address, int nbOrder, long id) {
        this.name = name;
        this.address = address;
        this.nbOrder = nbOrder;
        this.id = id;
    }

    /**
     * Instantiates a new Client.
     *
     * @param name    the name
     * @param address the address
     */
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

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets nb order.
     *
     * @return the nb order
     */
    public int getNbOrder() {
        return nbOrder;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
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
