package PdfHandler;

import Content.Vehicles.Vehicle;
import com.itextpdf.text.Font;

import java.util.ArrayList;

public interface PdfBuilder {
    void setTitle(String s, Font.FontFamily font, int color);
    void setSubTitle(String subtitle, Font.FontFamily font, int color);
    void setSections(ArrayList sections);
    void setVehicleSection(Vehicle vehicle);

}
