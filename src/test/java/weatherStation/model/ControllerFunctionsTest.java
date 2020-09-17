package weatherStation.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;
import java.net.UnknownHostException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;


class ControllerFunctionsTest extends ApplicationTest {

    private TextField currentCity;

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

    @BeforeEach
    void cleanUpTextFields() {

        currentCity = lookup("#currentCityTextField").query();
        currentCity.clear();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Warswa,PL"})
    void shouldShowErrorMessageWhenCityIsNotValid(String cityName) {

        // given
        Button button = lookup(".button").query();
        currentCity.setText(cityName);

        //when
        clickOn(button);

        //then
        FxAssert.verifyThat("#currentCityLabel", LabeledMatchers.hasText("Brak danych o podanym mieście."));
    }

}