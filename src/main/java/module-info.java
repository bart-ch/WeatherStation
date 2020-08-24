/**
 * Created by "Bartosz Chodyla" on 2020-08-24.
 */module WeatherStation {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;

    opens weatherStation;
    opens weatherStation.controller;

}