package weatherStation;/**
 * Created by "Bartosz Chodyla" on 2020-08-23.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import weatherStation.view.MainWindow;

public class Launcher extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.initializeStage();

    }
}
