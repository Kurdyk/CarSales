package PdfHandler;

import Content.Vehicles.Vehicle;

public abstract class Document {
    private Vehicle vehicle;
    private String path;
    public void setVehicle(Vehicle vehicle) {
        this.vehicle=vehicle;
    }
}
