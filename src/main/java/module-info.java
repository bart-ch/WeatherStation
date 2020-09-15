/** * Created by "Bartosz Chodyla" on 2020-08-24. */
module weatherStation {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires com.google.gson;
    requires org.controlsfx.controls;
    requires owm.japis;

    opens weatherStation;
    opens weatherStation.controller;
    opens weatherStation.model;
//    exports weatherStation.model;
//    exports weatherStation.model.city;
//    exports weatherStation.model.weather;
//    exports weatherStation.model.date;
//    exports weatherStation.view;
}