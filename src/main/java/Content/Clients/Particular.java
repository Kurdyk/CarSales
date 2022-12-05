package Content.Clients;

public class Particular extends Client {


    public Particular(String name, String address) {
        super(name, address);
    }

    public String toSQLFormat() {
        return this.id + ", '" + this.name + "', '" + this.address + "', " + this.nbOrder;
    }

    public Particular(String name, String address, int nbOrder, long id) {
        super(name, address, nbOrder, id);
    }

    @Override
    public String toString() {
        return "\"Particular\" : " + "\""+ this.name+"\"";
    }
}
