package weatherStation.model.weather;

import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.param.Weather;
import weatherStation.model.date.DateConverter;

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
        return null;
    }

    public String getCurrentPressure() {
        if (currentWeather.getMainData().hasPressure()) {
            return Math.round(currentWeather.getMainData().getPressure()) + " hPa";
        }
        return null;
    }

    public String getCurrentHumidity() {
        if (currentWeather.getMainData().hasHumidity()) {
            return Math.round(currentWeather.getMainData().getHumidity()) + " %";
        }
        return null;
    }

    public String getCurrentDate() {
        if (currentWeather.hasDateTime()) {
            String currentDate = currentWeather.getDateTime().toString();
            String polishCurrentDate = DateConverter.convertDateToPolish(currentDate);
            return polishCurrentDate;
        }
        return null;
    }

    public String getCurrentWeatherIcon() {
        net.aksingh.owmjapis.model.param.Weather currentWeatherData = getCurrentWeatherList().get(0);
        if (currentWeatherData.hasIconLink())
            return currentWeatherData.getIconLink();
        else return null;
    }

    private List<Weather> getCurrentWeatherList() {
        if (currentWeather.hasWeatherList())
            return currentWeather.getWeatherList();
        else return null;
    }

    public int getCurrentCondition() {
        Weather currentWeatherData = getCurrentWeatherList().get(0);
        return (currentWeatherData.hasConditionId()) ? currentWeatherData.getConditionId() : null;
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }
}
