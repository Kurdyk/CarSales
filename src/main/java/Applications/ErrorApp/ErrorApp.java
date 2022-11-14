package Applications.ErrorApp;

import Applications.ClientApp.ClientApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ErrorApp extends Application {

    private final String errorOrigin;

    public ErrorApp(String errorOrigin) {
        super();
        this.errorOrigin = errorOrigin;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ErrorApp.class.getResource("ErrorView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 125);
        stage.setTitle("An error has occurred, origin = " + errorOrigin);
        stage.setScene(scene);
        stage.show();
    }

}
