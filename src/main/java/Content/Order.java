package Content;

import Content.Clients.Client;
import Content.Vehicles.Vehicle;

/**
 * The type Order.
 */
public class Order {

    private final Client client;
    private final Vehicle vehicle;
    private boolean payed;

    /**
     * The enum Status.
     */
    public enum status {
        /**
         * Outgoing status.
         */
        OUTGOING,
        /**
         * Validated status.
         */
        VALIDATED,
        /**
         * Delivered status.
         */
        DELIVERED
    }

    /**
     * Is payed boolean.
     *
     * @return the boolean
     */
    public boolean isPayed() {
        return payed;
    }

    /**
     * Gets current status.
     *
     * @return the current status
     */
    public status getCurrentStatus() {
        return currentStatus;
    }

    private status currentStatus;

    /**
     * Instantiates a new Order.
     *
     * @param client  the client
     * @param vehicle the vehicle
     * @param payed   the payed
     */
    public Order(Client client, Vehicle vehicle, boolean payed) {
        this.client = client;
        this.vehicle = vehicle;
        this.payed = payed;
        this.currentStatus = status.OUTGOING;
    }

    /**
     * Instantiates a new Order.
     *
     * @param client        the client
     * @param vehicle       the vehicle
     * @param payed         the payed
     * @param currentStatus the current status
     */
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

    /**
     * To sql format string.
     *
     * @return the string
     */
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

    /**
     * Gets vehicle.
     *
     * @return the vehicle
     */
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    /**
     * Gets client.
     *
     * @return the client
     */
    public Client getClient() {
        return this.client;
    }

    @Override
    public String toString() {
        return "Client : " + this.client.toString() + " ordered " + this.vehicle.toString() +
                ", status: " + currentStatus.toString() + " payed: " + this.payed;
    }
}
