package PdfHandler;

import Content.Order;
import Content.Vehicles.Vehicle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Director {
    private Order order;

    public Director(Order order) {
        this.order=order;
    }
    public void constructRegistrationCertificate(PdfBuilder builder) throws DocumentException, FileNotFoundException {
        //TODO: mieux faire (ordre)

        builder.setPath("MyPdf.pdf");
        builder.setDocument();
        builder.setTitle("CERTIFICAT D'IMMATRICULATION DE VÉHICULE", Font.FontFamily.HELVETICA, new BaseColor(0,55,136));
        builder.setVehicleSection(order);
        builder.setClientSection(order);
        builder.ClientSignature(order);
        builder.AdministrationFrame(order);
    }

}
