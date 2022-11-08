package Content.Clients;

import Content.DataBase.DBConnector;

import java.sql.SQLException;

public class Client {

    private static long idClass = 0;

    protected final String name;
    protected final String address;
    protected final int nbOrder;
    protected final long id;

    public Client(String name, String address) {
        this.name = name;
        this.address = address;
        this.nbOrder = 0;
        if (idClass == 0) {
            try {
               idClass = DBConnector.instance.getNextAvailableId();
            } catch (SQLException e) {
                // todo error screen
                e.printStackTrace();
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
