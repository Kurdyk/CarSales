package PdfHandler;

import Content.Clients.Client;
import Content.Order;
import Content.Vehicles.Vehicle;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class Certificate extends PdfDocument {
    private Font titleFont;
    private Font subtitleFont;
    private Document document;

    public Certificate(Order o, String p, Font tf, Font sf, Document doc, String t){
        try {
            order=o;
            path=p;
            titleFont=tf;
            subtitleFont=sf;
            document=doc;
            title=t;
            /*document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            Paragraph ptitle = new Paragraph();
            ptitle.add(new Paragraph(title,titleFont));
            document.add(ptitle);
            document.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
