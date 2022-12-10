package PdfHandler;

import Content.Clients.Client;
import Content.Clients.Company;
import Content.Order;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * The type Certificate builder.
 */
public class CertificateBuilder implements PdfBuilder {
    private String path;
    private Font titleFont;
    private Font subtitleFont;
    private Document document;
    private String title;
    private Order order;
    private PdfPTable vehicleTable;
    private PdfPTable applicantTable;

    @Override
    public void setTitle(String title, Font.FontFamily font, BaseColor color) {
        titleFont = new Font(font,13,Font.BOLD,color);
        this.title=title;
        Paragraph ptitle = new Paragraph();
        ptitle.setAlignment(Element.ALIGN_CENTER);
        ptitle.add(new Paragraph(title,titleFont));
        try {
            document.add(ptitle);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setVehicleSection(Order order) {
        this.order=order;
        subtitleFont = new Font(Font.FontFamily.HELVETICA,12,Font.BOLD,BaseColor.WHITE);
        Chunk tmp = new Chunk("VÉHICULE",subtitleFont);
        tmp.setBackground(new BaseColor(0,55,136));
        Paragraph v = new Paragraph();
        v.add(tmp);

        PdfPTable table = new PdfPTable(3);
        Map line = makeMapVehicle();
        addLines(table,line,3);
        v.add(table);
        try {
            document.add(v);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setClientSection(Order order) {
        this.order=order;
        subtitleFont = new Font(Font.FontFamily.HELVETICA,12,Font.BOLD,BaseColor.WHITE);
        Chunk tmp = new Chunk("TITULAIRE",subtitleFont);
        tmp.setBackground(new BaseColor(0,55,136));
        Paragraph v = new Paragraph();
        v.add(tmp);

        PdfPTable table = new PdfPTable(3);
        Map line = makeMapClient();
        addLines(table,line,3);
        v.add(table);
        try {
            document.add(v);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ClientSignature(Order order) {
        this.order=order;
        subtitleFont = new Font(Font.FontFamily.HELVETICA,11,Font.NORMAL,BaseColor.BLACK);
        Chunk tmp = new Chunk("Le Titulaire",subtitleFont);
        Paragraph v = new Paragraph();
        v.add(tmp);
        tmp = new Chunk("Fait à :................. Le :...................",subtitleFont);
        Paragraph d = new Paragraph();
        d.add(tmp);
        tmp = new Chunk("Signature :            \n\n\n| ",subtitleFont);
        d.add(tmp);
        try {
            document.add(v);
            document.add(d);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void AdministrationFrame(Order order) {
        char unchecked='\u00A8';
        subtitleFont = new Font(Font.FontFamily.HELVETICA,11,Font.NORMAL,BaseColor.BLACK);
        Chunk tmp = new Chunk("Vu les pièces justificatives",subtitleFont);
        Paragraph p = new Paragraph();
        p.add(tmp);
        //PdfFormField checkbox1 = PdfFormField.createCheckBox();
        try {
            document.add(p);
            document.add(new Paragraph(String.valueOf(unchecked),subtitleFont));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    public Map makeMapVehicle() {
        Map l = new HashMap();
        if ((order.getVehicle()!=null) && (order.getClient()!=null)) {
            l.put("License Plate",order.getVehicle().getLicencePlate());
            l.put("Purchase date","../../..");
            l.put("Country of origin",order.getVehicle().getOriginCountry());
            l.put("Brand",order.getVehicle().getBrand());
            l.put("Model",order.getVehicle().getModel());
            l.put("Dominant color","??");
        } else {
            l.put("License Plate","");
            l.put("Adress","");
            l.put("SIRET","");
        }
        return l;
    }

    /**
     * Make map client map.
     *
     * @return the map
     */
    public Map makeMapClient() {
        Map l = new HashMap();
        if ((order.getVehicle()!= null) && (order.getClient()!=null)) {
            l.put("Name",order.getClient().getName());
            l.put("Adress",order.getClient().getAddress());
            if (order.getClient() instanceof Company) {
                l.put("SIRET",((Company) order.getClient()).getSiret());
            } else {
                l.put("SIRET","");
            }
        } else {
            l.put("Name","");
            l.put("Adress","");
            l.put("SIRET","");
        }
        return l;
    }



    @Override
    public void setPath(String path) {
        this.path=path;
    }

    @Override
    public void setDocument() throws FileNotFoundException, DocumentException {
        document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();

        System.out.println("opening...");

    }

    /**
     * Build certificate.
     *
     * @return the certificate
     * @throws FileNotFoundException the file not found exception
     * @throws DocumentException     the document exception
     */
    public Certificate build() throws FileNotFoundException, DocumentException {
        document.close();
        return new Certificate(order,path,titleFont, subtitleFont,document,title);
    }


}
