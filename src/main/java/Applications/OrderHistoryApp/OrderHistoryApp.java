package Applications.OrderHistoryApp;

import Applications.MainApp.MainApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OrderHistoryApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(OrderHistoryApp.class.getResource("OrderHistoryView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("A car vendor App");
        stage.setScene(scene);
        stage.show();
    }
}
