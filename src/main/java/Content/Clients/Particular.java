package Content.Clients;

public class Particular extends Client {


    public Particular(String name, String address) {
        super(name, address);
    }

    @Override
    public String toString() {
        return "Particular{" +
                " name= " + this.getName() +
                " address= " + this.getAddress() +
                "}";
    }
}
