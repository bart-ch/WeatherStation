package weatherStation.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private TextField desiredCityTextField;

    @FXML
    private Label currentCityLabel;

    @FXML
    private Label currentDateForCurrentCityLabel;

    @FXML
    private Label desiredCityLabel;

    @FXML
    private Label currentDateForDesiredCityLabel;

    @FXML
    private Label currentTempForCurrentCity;

    private ControllerFunctions controllerFunctions;

    @FXML
    void currentCityButtonAction() {

    }

    @FXML
    void desiredCityButtonAction() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            controllerFunctions = new ControllerFunctions(currentCityTextField, desiredCityTextField);
            Platform.runLater(() -> currentCityTextField.requestFocus());
    }
}





