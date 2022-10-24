package Applications.OrderHistoryApp;

import Content.Order;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import com.itextpdf.text.Document;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class OrderHistoryController {

    @FXML
    private ListView<Order> orderListView;

    @FXML
    private void onExportAsPDFClick() throws FileNotFoundException, DocumentException {
        System.out.println("PDF click");
        Document document = new Document();
        FileOutputStream fileOutputStream = new FileOutputStream("/home/louis/Desktop/testdoc.pdf");
        PdfWriter pdfWriter = PdfWriter.getInstance(document, fileOutputStream);
        document.open();
        document.add(new Paragraph("Vous allez payer"));
        document.close();
        pdfWriter.close();
    }

    @FXML
    void onExportAsHTMLClick() {

    }
}
