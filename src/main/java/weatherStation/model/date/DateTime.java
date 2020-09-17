package weatherStation.model.date;

import net.aksingh.owmjapis.model.CurrentWeather;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by "Bartosz Chodyla" on 2020-09-13.
 */
public class DateTime {

    private CurrentWeather currentWeather;

    public DateTime(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public String getCurrentTime() {
        if (currentWeather.hasDateTime()) {
            String currentDateTime = currentWeather.getDateTime().toString();
            String time = currentDateTime.substring(11, 13) + "." + currentDateTime.substring(14, 16)
                    + currentDateTime.substring(17, 19);
            return time;
        } else {
            return null;
        }

    }

    public String getSunsetTime() {
        if (currentWeather.getSystemData().hasSunsetDateTime()) {
            String sunsetDateTime = currentWeather.getSystemData().getSunsetDateTime().toString();
            String time = sunsetDateTime.substring(11, 13) + "." + sunsetDateTime.substring(14, 16)
                    + sunsetDateTime.substring(17, 19);
            return time;
        } else {
            return null;
        }
    }

    public String getSunriseTime() {
        if (currentWeather.getSystemData().hasSunriseDateTime()) {
            String sunriseDateTime = currentWeather.getSystemData().getSunriseDateTime().toString();
            String time = sunriseDateTime.substring(11, 13) + "." + sunriseDateTime.substring(14, 16)
                    + sunriseDateTime.substring(17, 19);
            return time;
        } else {
            return null;
        }
    }
}
