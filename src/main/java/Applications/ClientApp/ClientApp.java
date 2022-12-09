package Applications.ClientApp;

import Applications.MainApp.MainApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The type Client app.
 */
public class ClientApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("ClientView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("A car vendor App");
        stage.setScene(scene);
        stage.show();
    }
}
