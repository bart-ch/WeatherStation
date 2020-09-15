//package weatherStation.model;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.testfx.framework.junit5.ApplicationTest;
//
//import java.io.IOException;
//
//import static org.testfx.api.FxAssert.verifyThat;
//import static org.testfx.matcher.control.LabeledMatchers.hasText;
//
///**
// * Created by "Bartosz Chodyla" on 2020-09-15.
// */
//public class ControllerFunctionsTest extends ApplicationTest {
//
//    private TextField currentCity;
//    private  TextField desiredCity;
//
//    @Override
//    public void start(Stage stage) {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
//        Parent parent;
//        try {
//            parent = fxmlLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        Scene scene = new Scene(parent);
//        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
//        stage.setScene(scene);
//
//        stage.setTitle("Wakacyjna stacja pogodowa");
//        stage.setResizable(false);
//        stage.show();
//    }
//
//    @BeforeEach
//    void cleanUpTextFields() {
//
//        currentCity = lookup("#currentCityTextField").query();
//        desiredCity = lookup("#desiredCityTextField").query();
//        currentCity.clear();
//        desiredCity.clear();
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"", "Warszawa,PL"})
//    void shouldBeAbleToShowErrorMessageForUserCityIfCityIdIsIncorrect(String cityName) {
//
//        //given
//        Button button = lookup("#currentCityButtonAction").lookup(".button").query();
//
//        //when
//        currentCity.setText(cityName);
//        clickOn(button);
//
//        //then
//        verifyThat("#currentCityLabel", hasText("Brak danych o podanym mieście."));
//    }
//
//}
