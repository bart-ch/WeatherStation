package weatherStation.model;

import weatherStation.model.date.DateTime;

import java.text.ParseException;

/**
 * Created by "Bartosz Chodyla" on 2020-09-12.
 */
public class ImagePathProvider {

    public static String getBackgroundImagePath(DateTime dateTime, int conditionId) {

        String currentDateTime = dateTime.getCurrentTime();
        String sunriseDateTime = dateTime.getSunriseTime();
        String sunsetDateTime = dateTime.getSunsetTime();

        double sunriseHour = Double.parseDouble(currentDateTime);
        double sunsetHour = Double.parseDouble(sunriseDateTime);
        double currentHour = Double.parseDouble(sunsetDateTime);

        String conditionImageURL;

        if ((currentHour > sunriseHour) && (currentHour < sunsetHour)) {
            if ((conditionId >= 200) && (conditionId <= 232)) {
                conditionImageURL = "/img/thunderstorm_day.jpg";
            } else if ((conditionId >= 300) && (conditionId <= 531)) {
                conditionImageURL = "/img/rain_day.jpg";
            } else if ((conditionId >= 600) && (conditionId <= 622)) {
                conditionImageURL = "/img/snow_day.jpg";
            } else if ((conditionId >= 701) && (conditionId <= 781)) {
                conditionImageURL = "/img/fog_day.jpg";
            } else if ((conditionId >= 801) && (conditionId <= 804)) {
                conditionImageURL = "/img/clouds_day.jpg";
            } else if ((conditionId == 800)) {
                conditionImageURL = "/img/sun.jpg";
            } else conditionImageURL = "";
        } else {
            if ((conditionId >= 200) && (conditionId <= 232)) {
                conditionImageURL = "/img/thunderstorm_night.jpg";
            } else if ((conditionId >= 300) && (conditionId <= 531)) {
                conditionImageURL = "/img/rain_night.jpg";
            } else if ((conditionId >= 600) && (conditionId <= 622)) {
                conditionImageURL = "/img/snow_night.jpg";
            } else if ((conditionId >= 701) && (conditionId <= 781)) {
                conditionImageURL = "/img/fog_night.jpg";
            } else if ((conditionId >= 801) && (conditionId <= 804)) {
                conditionImageURL = "/img/clouds_night.jpg";
            } else if ((conditionId == 800)) {
                conditionImageURL = "/img/moon.jpg";
            } else conditionImageURL = "";
        }

        return conditionImageURL;
    }

}
