package PdfHandler;

import Content.Clients.Company;
import Content.Order;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PurchaseBuilder implements PdfBuilder {
    private String path;
    private Font titleFont;
    private Font subtitleFont;
    private Document document;
    private String title;
    private Order order;

    @Override
    public void setTitle(String s, Font.FontFamily font, BaseColor color) {
        titleFont = new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.WHITE);
        this.title = s;
        Chunk tmp = new Chunk(title,titleFont);
        tmp.setBackground(new BaseColor(3,102,177));
        Paragraph v = new Paragraph();
        v.add(tmp);
        try {
            document.add(v);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
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
    }

    @Override
    public void setVehicleSection(Order order) {
        this.order=order;
        subtitleFont = new Font(Font.FontFamily.HELVETICA,12,Font.BOLD,BaseColor.WHITE);
        Chunk tmp = new Chunk("VÉHICULE NEUF",subtitleFont);
        tmp.setBackground(new BaseColor(3,102,177));
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

        public Map makeMapDelivery() {
        Map l = new HashMap();
            if (order.getVehicle() == null) {
                l.put("Price","");
                l.put("Place of delivery ","");
            } else {
                l.put("Price",order.getVehicle().getLicencePlate());
                l.put("Place of delivery ",order.getClient().getAddress());
            }
        return l;
    }



    @Override
    public void setClientSection(Order order) {
        Paragraph v = new Paragraph();
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
    public void ClientSignature(Order order) {
        this.order=order;
        subtitleFont = new Font(Font.FontFamily.HELVETICA,11,Font.NORMAL,new BaseColor(3,102,177));
        Chunk tmp = new Chunk("L'acheteur",subtitleFont);
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
        subtitleFont = new Font(Font.FontFamily.HELVETICA,11,Font.NORMAL,new BaseColor(3,102,177));
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

    public Certificate build() throws FileNotFoundException, DocumentException {
        document.close();
        return new Certificate(order,path,titleFont, subtitleFont,document,title);
    }
}
