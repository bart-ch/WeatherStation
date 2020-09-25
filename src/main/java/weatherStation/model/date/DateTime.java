package weatherStation.model.date;

import net.aksingh.owmjapis.model.CurrentWeather;
import weatherStation.model.weather.CurrentWeatherData;

/**
 * Created by "Bartosz Chodyla" on 2020-09-13.
 */
public class DateTime {

    private CurrentWeatherData currentWeather;

    public DateTime(CurrentWeatherData currentWeather) {
        this.currentWeather = currentWeather;
    }

    public String getCurrentTime() {

        String currentDateTime = currentWeather.getCurrentDateTime();
        if (!currentDateTime.isEmpty()) {
            String currentTime = currentDateTime.substring(11, 13) + "." + currentDateTime.substring(14, 16)
                    + currentDateTime.substring(17, 19);
            return currentTime;
        } else {
            return "";
        }
    }

    public String getSunsetTime() {

        String sunsetDateTime = currentWeather.getSunsetDateTime();
        if (!sunsetDateTime.isEmpty()) {
            String sunsetTime = sunsetDateTime.substring(11, 13) + "." + sunsetDateTime.substring(14, 16)
                    + sunsetDateTime.substring(17, 19);
            return sunsetTime;
        } else {
            return "";
        }
    }

    public String getSunriseTime() {
        String sunriseDateTime = currentWeather.getSunriseDateTime();
        if (!sunriseDateTime.isEmpty()) {
            String sunriseTime = sunriseDateTime.substring(11, 13) + "." + sunriseDateTime.substring(14, 16)
                    + sunriseDateTime.substring(17, 19);
            return sunriseTime;
        } else {
            return "";
        }
    }
}
