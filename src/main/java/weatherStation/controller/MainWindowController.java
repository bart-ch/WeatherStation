package weatherStation.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import weatherStation.model.ControllerFunctions;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by "Bartosz Chodyla" on 2020-08-24.
 */

public class MainWindowController implements Initializable {

    @FXML
    private TextField currentCityTextField;

    @FXML
    private Label currentCityLabel;

    @FXML
    private Label currentDateForCurrentCityLabel;

    @FXML
    private Label currentCityNowLabel;

    @FXML
    private Label currentTempForCurrentCityLabel;

    @FXML
    private ImageView currentWeatherIconForCurrentCity;

    @FXML
    private Label currentPressureForCurrentCity;

    @FXML
    private Label currentHumidityForCurrentCity;

    @FXML
    private HBox currentCityCurrentDayNextHoursWeather;

    @FXML
    private Label desiredCityLabel;

    @FXML
    private Label currentDateForDesiredCityLabel;


    @FXML
    private GridPane currentCityGridPane;

    @FXML
    private TextField desiredCityTextField;

    @FXML
    private GridPane weatherForNextDaysForCurrentCity;

    private ControllerFunctions controllerFunctions;

    @FXML
    void currentCityButtonAction() {
       controllerFunctions.loadWeather(currentCityTextField,currentCityLabel,
               currentTempForCurrentCityLabel, currentDateForCurrentCityLabel, currentCityNowLabel,
               currentPressureForCurrentCity, currentHumidityForCurrentCity, currentCityCurrentDayNextHoursWeather,
               currentWeatherIconForCurrentCity,weatherForNextDaysForCurrentCity, currentCityGridPane);

    }

    @FXML
    void desiredCityButtonAction() {
        //controllerFunctions.loadWeatherForCurrentDay(desiredCityTextField,desiredCityLabel);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            controllerFunctions = new ControllerFunctions(currentCityTextField, desiredCityTextField);
            Platform.runLater(() -> currentCityTextField.requestFocus());
    }
}





