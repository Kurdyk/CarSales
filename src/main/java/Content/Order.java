package Content;

import Content.Clients.Client;
import Content.Vehicles.Vehicle;

public class Order {

    private Client client;
    private Vehicle vehicle;
    private boolean payed;
    private enum status {
        OUTGOING,
        VALIDATED,
        DELIVERED
    }
    private status currentStatus;

    public Order(Client client, Vehicle vehicle, boolean payed) {
        this.client = client;
        this.vehicle = vehicle;
        this.payed = payed;
        this.currentStatus = status.OUTGOING;
    }

    public Order(Client client, Vehicle vehicle, boolean payed, String currentStatus) {
        this.client = client;
        this.vehicle = vehicle;
        this.payed = payed;
        switch (currentStatus) {
            case "OUTGOING":
                this.currentStatus = status.OUTGOING;
                break;
            case "VALIDATED":
                this.currentStatus = status.VALIDATED;
                break;
            case "DELIVERED":
                this.currentStatus = status.DELIVERED;
                break;
            default:
                System.out.println("Error while parsing order status");
                System.exit(1);
        }
    }

    public String toSQLFormat() {
        String currentStatus = null;
        switch (this.currentStatus) {
            case OUTGOING -> currentStatus = "'OUTGOING'";
            case VALIDATED -> currentStatus = "'VALIDATED'";
            case DELIVERED -> currentStatus = "'DELIVERED'";
            default -> {
                System.out.println("Error reading order status");
                System.exit(1);
            }
        }
        String payed;
        if (this.payed) {
            payed = "TRUE";
        } else {
            payed = "FALSE";
        }
        return this.client.getId() + ", " +this.vehicle.getId() + ", " + currentStatus + ", " + payed;
    }

    @Override
    public String toString() {
        return "Client" + this.client.toString() + " ordered " + this.vehicle.toString() +
                ", status: " + currentStatus.toString() + " payed: " + this.payed;
    }
}
