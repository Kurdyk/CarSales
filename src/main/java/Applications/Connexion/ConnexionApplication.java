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

public class ConnexionApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConnexionApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        stage.setTitle("A car vendor App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws DocumentException, FileNotFoundException {
        //launch();
        /*
        pdf building tests
        * */

        Client cli = new Client("Quidam","2 rue Victor Hugo",2,2343);
        Vehicle vehicle = new Vehicle("Renault","GG-246-KM",30000,new Date(2015,4,3),"FRANCE","Espace");
        Order order = new Order(cli,vehicle);

        Director director = new Director(order);
        CertificateBuilder builder = new CertificateBuilder();
        director.constructRegistrationCertificate(builder);

        Certificate immat = builder.build();
    }
}