package weatherStation;/**
 * Created by "Bartosz Chodyla" on 2020-08-23.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import weatherStation.model.CityProvider;
import weatherStation.view.MainWindow;

public class Launcher extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainWindow mainWindow = new MainWindow();
        mainWindow.initializeStage();

    }
}
