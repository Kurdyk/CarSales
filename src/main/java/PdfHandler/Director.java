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

/**
 * The type Director.
 */
public class Director {
    private Order order;

    /**
     * Instantiates a new Director.
     *
     * @param order the order
     */
    public Director(Order order) {
        this.order=order;
    }

    /**
     * Construct registration certificate.
     *
     * @param builder the builder
     * @param path    the path
     * @throws DocumentException     the document exception
     * @throws FileNotFoundException the file not found exception
     */
    public void constructRegistrationCertificate(PdfBuilder builder,String path) throws DocumentException, FileNotFoundException {
        builder.setPath(path);
        builder.setDocument();
        builder.setTitle("CERTIFICAT D'IMMATRICULATION DE VÉHICULE", Font.FontFamily.HELVETICA, new BaseColor(0,55,136));
        builder.setVehicleSection(order);
        builder.setClientSection(order);
        builder.ClientSignature(order);
        builder.AdministrationFrame(order);
    }

    /**
     * Construct transfer certificate.
     *
     * @param builder the builder
     * @param path    the path
     * @throws DocumentException     the document exception
     * @throws FileNotFoundException the file not found exception
     */
    public void constructTransferCertificate(PdfBuilder builder, String path) throws DocumentException, FileNotFoundException  {
        builder.setPath(path);
        builder.setDocument();
        builder.setTitle("CERTIFICAT DE CESSION DE VÉHICULE",Font.FontFamily.HELVETICA, new BaseColor(102,0,204));
        builder.setVehicleSection(order);
        builder.setClientSection(order);
        builder.ClientSignature(order);
        builder.AdministrationFrame(order);
    }

    /**
     * Construct purchase order.
     *
     * @param builder the builder
     * @param path    the path
     * @throws DocumentException     the document exception
     * @throws FileNotFoundException the file not found exception
     */
    public void constructPurchaseOrder(PdfBuilder builder, String path) throws DocumentException, FileNotFoundException  {
        builder.setPath(path);
        builder.setDocument();
        builder.setTitle("BON DE COMMANDE",Font.FontFamily.HELVETICA,new BaseColor(0,102,204));
        builder.setVehicleSection(order);
        builder.setClientSection(order);
        builder.ClientSignature(order);
        builder.AdministrationFrame(order);
    }

}
