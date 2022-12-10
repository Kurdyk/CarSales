package PdfHandler;

import Content.Order;
import Content.Vehicles.Vehicle;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

public interface PdfBuilder {
    void setTitle(String s, Font.FontFamily font, BaseColor color);

    void setPath(String path);

    void setDocument() throws FileNotFoundException, DocumentException;

    void setVehicleSection(Order order);

    void setClientSection(Order order);

    void ClientSignature(Order order);

    void AdministrationFrame(Order order);

    default void addLines(PdfPTable table, Map<String, String> line, int columns) {
        int i = 0;
        ArrayList tmp = new ArrayList<>();
        for (String k1 : line.keySet()) {
            PdfPCell c1 = new PdfPCell(new Phrase(Font.BOLD, k1));
            tmp.add(line.get(k1));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            i++;
            if (i % columns == 0 && i != 0) {
                tmp.forEach(v -> table.addCell((String) v));
                tmp.clear();
            }
        }
    }


}
