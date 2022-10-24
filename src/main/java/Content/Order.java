package Content;

import Content.Clients.Client;
import Content.Vehicles.Vehicle;

public class Order {

    private static long nbOrders = 0;
    private long id;
    private Client client;
    private Vehicle vehicle;
    private boolean payed;
    private enum status {
        OUTGOING,
        VALIDATED,
        DELIVERED
    }
    private status currentStatus;

    public Order(Client client, Vehicle vehicle) {
        this.client = client;
        this.vehicle = vehicle;
        this.payed = false;
        this.currentStatus = status.OUTGOING;
        this.id = nbOrders++;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", vehicle=" + vehicle +
                ", payed=" + payed +
                ", currentStatus=" + currentStatus +
                '}';
    }
}
