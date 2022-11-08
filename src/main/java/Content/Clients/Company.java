package Content.Clients;

public class Company extends Client {

    private final String siret;

    public Company(String name, String address, String siret) {
        super(name, address);
        this.siret = siret;
    }

    public String getSiret() {
        return siret;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nbOrder=" + nbOrder +
                ", id=" + id +
                ", siret='" + siret + '\'' +
                '}';
    }
}