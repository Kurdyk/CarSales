package Content.Clients;

public class Client {

   protected final String name;
   protected String address;
   protected int nbOrder;

    public Client(String name, String address) {
        this.name = name;
        this.address = address;
        this.nbOrder = 0;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
                '}';
    }
}
