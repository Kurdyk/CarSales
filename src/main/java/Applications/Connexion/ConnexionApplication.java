package Applications.Connexion;

import Content.Clients.Client;
import Content.Order;
import Content.Vehicles.Vehicle;
import PdfHandler.Certificate;
import PdfHandler.CertificateBuilder;
import PdfHandler.Director;
import com.itextpdf.text.DocumentException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * The type Connexion application.
 */
public class ConnexionApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConnexionApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        stage.setTitle("A car vendor App");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws DocumentException     the document exception
     * @throws FileNotFoundException the file not found exception
     */
    public static void main(String[] args) throws DocumentException, FileNotFoundException {
        launch();
    }
}