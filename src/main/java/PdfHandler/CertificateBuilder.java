package PdfHandler;

import Content.Clients.Client;
import Content.Vehicles.Vehicle;
import com.itextpdf.text.Font;
import java.util.ArrayList;

public class CertificateBuilder implements PdfBuilder {
    private Vehicle vehicle;
    private Client applicant;
    private String path;
    private Font titleFont = new Font(Font.FontFamily.HELVETICA,14);
    private Font subtitleFont;
    private Document doc;

    @Override
    public void setTitle(String title, Font.FontFamily font, int color) {

    }

    @Override
    public void setSubTitle(String subtitle, Font.FontFamily font, int color) {

    }

    @Override
    public void setSections(ArrayList sections) {

    }

    @Override
    public void setVehicleSection(Vehicle vehicle) {

    }



    public Certificate getCertificate() {
        return null;
    }


}
