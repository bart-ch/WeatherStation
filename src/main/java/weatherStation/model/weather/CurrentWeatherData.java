package weatherStation.model.weather;

import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.param.Weather;
import weatherStation.model.date.DateConverter;

import java.util.Collections;
import java.util.List;

/**
 * Created by "Bartosz Chodyla" on 2020-09-12.
 */
public class CurrentWeatherData {

    private CurrentWeather currentWeather;

    public CurrentWeatherData(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public String getCurrentTemperature() {
        if (currentWeather.getMainData().hasTemp()) {
            return WeatherOperations.roundTemperature(currentWeather.getMainData().getTemp()) + WeatherOperations.getDegreeSymbol() +
                    "C";
        }
        return "";
    }

    public String getCurrentPressure() {
        if (currentWeather.getMainData().hasPressure()) {
            return Math.round(currentWeather.getMainData().getPressure()) + " hPa";
        }
        return "";
    }

    public String getCurrentHumidity() {
        if (currentWeather.getMainData().hasHumidity()) {
            return Math.round(currentWeather.getMainData().getHumidity()) + " %";
        }
        return "";
    }

    public String getCurrentDate() {
        if (currentWeather.hasDateTime()) {
            String currentDate = currentWeather.getDateTime().toString();
            String polishCurrentDate = DateConverter.convertDateToPolish(currentDate);
            return polishCurrentDate;
        }
        return "";
    }

    public String getCurrentWeatherIconLink() {
        Weather currentWeatherData = getCurrentWeatherList().get(0);
        if (currentWeatherData.hasIconLink()) {
            return currentWeatherData.getIconLink();
        }
        return "";
    }

    private List<Weather> getCurrentWeatherList() {
        if (currentWeather.hasWeatherList()) {
            return currentWeather.getWeatherList();
        }
        return Collections.emptyList();
    }

    public int getCurrentCondition() {
        Weather currentWeatherData = getCurrentWeatherList().get(0);
        return (currentWeatherData.hasConditionId()) ? currentWeatherData.getConditionId() : 0;
    }

    public String getCurrentDateTime() {
        if (currentWeather.hasDateTime()) {
            String currentDateTime = currentWeather.getDateTime().toString();
            return currentDateTime;
        } else {
            return "";
        }

    }

    public String getSunsetDateTime() {
        if (currentWeather.getSystemData().hasSunsetDateTime()) {
            String sunsetDateTime = currentWeather.getSystemData().getSunsetDateTime().toString();
            return sunsetDateTime;
        } else {
            return "";
        }
    }

    public String getSunriseDateTime() {
        if (currentWeather.getSystemData().hasSunriseDateTime()) {
            String sunriseDateTime = currentWeather.getSystemData().getSunriseDateTime().toString();
            return sunriseDateTime;
        } else {
            return "";
        }
    }

}
