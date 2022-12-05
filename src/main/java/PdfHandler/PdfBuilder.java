package PdfHandler;

import Content.Order;
import Content.Vehicles.Vehicle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

public interface PdfBuilder {
    void setTitle(String s, Font.FontFamily font, BaseColor color);
    void setSubTitle(String subtitle, Font.FontFamily font, int color);
    void setSections(ArrayList sections);
    void setPath(String path);
    void setDocument() throws FileNotFoundException, DocumentException;
    void setVehicleSection(Order order);
    void setSection(Order order,String title, int columns);
    void setSectionBis(Order order);
    void setClientSection(Order order);
    void ClientSignature(Order order);
    void AdministrationFrame(Order order);

}
