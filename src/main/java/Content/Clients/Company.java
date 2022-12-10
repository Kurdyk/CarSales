package Content.Clients;

/**
 * The type Company.
 */
public class Company extends Client {

    private final String siret;

    /**
     * Instantiates a new Company.
     *
     * @param name    the name
     * @param address the address
     * @param siret   the siret
     */
    public Company(String name, String address, String siret) {
        super(name, address);
        this.siret = siret;
    }

    /**
     * Gets siret.
     *
     * @return the siret
     */
    public String getSiret() {
        return siret;
    }

    /**
     * To sql format string.
     *
     * @return the string
     */
    public String toSQLFormat() {
        return this.id + ", '" + this.name + "', '" + this.address + "', " + this.nbOrder + ", '" + this.siret + "'";
    }

    /**
     * Instantiates a new Company.
     *
     * @param name    the name
     * @param address the address
     * @param nbOrder the nb order
     * @param id      the id
     * @param siret   the siret
     */
    public Company(String name, String address, int nbOrder, long id, String siret) {
        super(name, address, nbOrder, id);
        this.siret = siret;
    }

    @Override
    public String toString() {
        return "Company : " + this.name;
    }
}
