package Content.Clients;

/**
 * The type Particular.
 */
public class Particular extends Client {


    /**
     * Instantiates a new Particular.
     *
     * @param name    the name
     * @param address the address
     */
    public Particular(String name, String address) {
        super(name, address);
    }

    /**
     * To sql format string.
     *
     * @return the string
     */
    public String toSQLFormat() {
        return this.id + ", '" + this.name + "', '" + this.address + "', " + this.nbOrder;
    }

    /**
     * Instantiates a new Particular.
     *
     * @param name    the name
     * @param address the address
     * @param nbOrder the nb order
     * @param id      the id
     */
    public Particular(String name, String address, int nbOrder, long id) {
        super(name, address, nbOrder, id);
    }

    @Override
    public String toString() {
        return "Particular : " + this.name;
    }
}
