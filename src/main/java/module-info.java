/**
 * Created by "Bartosz Chodyla" on 2020-08-24.
 */module WeatherStation {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires com.google.gson;

    opens weatherStation;
    opens weatherStation.controller;
    opens weatherStation.model;

}