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


public class CertificateBuilder implements PdfBuilder {
    private String path;
    private Font titleFont;
    private Font subtitleFont;
    private Document document;
    private String title;
    private Order order;
    private PdfPTable vehicleTable;
    private PdfPTable applicantTable;
    //TODO: add Paragraph object as an attribute?

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
    public void setSectionBis(Order order) {
        this.order=order;
        subtitleFont = new Font(Font.FontFamily.HELVETICA,12,Font.BOLD,BaseColor.WHITE);
        Chunk tmp = new Chunk("VÉHICULE",subtitleFont);
        tmp.setBackground(new BaseColor(0,55,136));
        Paragraph v = new Paragraph();
        v.add(tmp);
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


    @Override
    public void setSection(Order order, String title,int columns) {
        this.order=order;
        subtitleFont = new Font(Font.FontFamily.HELVETICA,12,Font.BOLD,BaseColor.WHITE);
        Chunk tmp = new Chunk(title,subtitleFont);
        tmp.setBackground(new BaseColor(0,55,136));
        Paragraph v = new Paragraph();
        v.add(tmp);

        PdfPTable table = new PdfPTable(columns);
        Map line=null; //= makeMapVehicle();
        addLines(vehicleTable,line,columns);
        v.add(vehicleTable);
        try {
            document.add(v);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }



    public Map StringToMap() {
        //todo: add json lib

        return null;
    }

    public Map makeMapVehicle() {
        Map l = new HashMap();
        //TODO: faire d'après le json
        /*JSONObject orderjson = new JSONObject(order.toString());
        String cli = orderjson.getString("client");
        */

        l.put("License Plate",order.getVehicle().getLicencePlate());
        l.put("Purchase date","../../..");
        l.put("Country of origin",order.getVehicle().getOriginCountry());
        l.put("Brand",order.getVehicle().getBrand());
        l.put("Model",order.getVehicle().getModel());
        l.put("Dominant color","??");
        return l;
    }

    public Map makeMapClient() {
        Map l = new HashMap();
        l.put("Name",order.getClient().getName());
        l.put("Adress",order.getClient().getAddress());
        l.put("SIRET","");
        //TODO: ajouter siret si company
        return l;
    }
        public void addLines(PdfPTable table,Map<String,String> line, int columns) {
        int i=0;
        int k=0;
        ArrayList tmp=new ArrayList<>();
           for (String k1 : line.keySet()) {
               System.out.println("k1:"+k1);
               System.out.println("i:"+i);
               PdfPCell c1 = new PdfPCell(new Phrase(Font.BOLD, k1));
               System.out.println("tmp.add"+line.get(k1));
               tmp.add(line.get(k1));
               c1.setHorizontalAlignment(Element.ALIGN_CENTER);
               table.addCell(c1);
               i++;
               if (i%columns==0 && i!=0) {
                   tmp.forEach(v->table.addCell((String)v));
                   tmp.clear();
               }
           }
    }




    @Override
    public void setSubTitle(String subtitle, Font.FontFamily font, int color) {

    }



    @Override
    public void setSections(ArrayList sections) {

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
        //System.out.println("opening...");

    }

    public Certificate build() throws FileNotFoundException, DocumentException {
        document.close();
        return new Certificate(order,path,titleFont, subtitleFont,document,title);
    }


}
