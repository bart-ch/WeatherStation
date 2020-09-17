/**
 * Created by "Bartosz Chodyla" on 2020-09-17.
 */module WeatherStation {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires com.google.gson;
    requires org.controlsfx.controls;
    requires owm.japis;

    opens weatherStation;
    opens weatherStation.controller;
    opens weatherStation.model;

}