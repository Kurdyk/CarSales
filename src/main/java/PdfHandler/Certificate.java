package PdfHandler;

import Content.Clients.Client;
import Content.Order;
import Content.Vehicles.Vehicle;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

/**
 * The type Certificate.
 */
public class Certificate extends PdfDocument {
    private Font titleFont;
    private Font subtitleFont;
    private Document document;

    /**
     * Instantiates a new Certificate.
     *
     * @param o   the o
     * @param p   the p
     * @param tf  the tf
     * @param sf  the sf
     * @param doc the doc
     * @param t   the t
     */
    public Certificate(Order o, String p, Font tf, Font sf, Document doc, String t){
        try {
            order=o;
            path=p;
            titleFont=tf;
            subtitleFont=sf;
            document=doc;
            title=t;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
