package weatherStation.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;


/**
 * Created by "Bartosz Chodyla" on 2020-09-16.
 */
public class MainWindowTest extends ApplicationTest {

    private TextField currentCity;
    private TextField desiredCity;

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);

        stage.setTitle("Wakacyjna stacja pogodowa");
        stage.setResizable(false);
        stage.show();
    }


    @Test
    void mainWindowShouldOpen() {

        //then
        FxAssert.verifyThat(window("Wakacyjna stacja pogodowa"), WindowMatchers.isShowing());
    }
}