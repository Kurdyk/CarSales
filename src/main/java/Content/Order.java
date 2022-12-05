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

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public Client getClient() {
        return this.client;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"client\":" + client.toString() +
                ",\"vehicle\":" + vehicle +
                ",\"payed\":" + payed +
                ",\"currentStatus\":\"" + currentStatus +"\"" +
                '}';
    }
}
