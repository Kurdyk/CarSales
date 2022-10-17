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


}
