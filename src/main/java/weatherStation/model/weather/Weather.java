package weatherStation.model.weather;

import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.WeatherData;
import weatherStation.model.date.DateConverter;

import java.util.List;

/**
 * Created by "Bartosz Chodyla" on 2020-09-12.
 */
public class Weather {

    private CurrentWeather currentWeather;
    private HourlyWeatherForecast hourlyWeatherForecast;
    private final String DEGREE_SYMBOL = "\u00B0";

    public Weather(CurrentWeather currentWeather, HourlyWeatherForecast hourlyWeatherForecast) {
        this.currentWeather = currentWeather;
        this.hourlyWeatherForecast = hourlyWeatherForecast;
    }

    public String getCurrentTemperature() {
        if(currentWeather.getMainData().hasTemp()) {
            return roundTemperature(currentWeather.getMainData().getTemp()) + DEGREE_SYMBOL + "C";
        }
        return null;
    }

    public String getCurrentPressure() {
        if(currentWeather.getMainData().hasPressure()) {
            return Math.round(currentWeather.getMainData().getPressure()) + " hPa";
        }
        return null;
    }

    public String getCurrentHumidity() {
        if(currentWeather.getMainData().hasHumidity()) {
            return Math.round(currentWeather.getMainData().getHumidity()) + " %";
        }
        return null;
    }

    private Double roundTemperature(Double temp) {
        return Math.round(temp * 10.0) /10.0;
    }

    public String getCityName() {
        return (currentWeather.hasCityName()) ? currentWeather.getCityName() : null;
    }

    public String getCountryCode() {
        return (hourlyWeatherForecast.hasCityData()) ? hourlyWeatherForecast.getCityData().getCountryCode() : null;
    }

    public String getCurrentDate() {
        if(currentWeather.hasDateTime()) {
            String currentDate = currentWeather.getDateTime().toString();
            String polishCurrentDate = DateConverter.convertDateToPolish(currentDate);
            return polishCurrentDate;
        }
        return null;
    }

    public String getCurrentDateTime() {
        return (currentWeather.hasDateTime()) ? currentWeather.getDateTime().toString() : null;
    }

    public String getSunsetDateTime() {
        return (currentWeather.getSystemData().hasSunsetDateTime()) ? currentWeather.getSystemData().getSunsetDateTime().toString() :
                null;
    }

    public String getSunriseDateTime() {
        return (currentWeather.getSystemData().hasSunriseDateTime()) ? currentWeather.getSystemData().getSunriseDateTime().toString() :
                null;
    }

    public List<WeatherData> getHourlyWeatherDataList() {
        return (hourlyWeatherForecast.hasDataList()) ? hourlyWeatherForecast.getDataList() : null;
    }

    private WeatherData getHourlyWeatherData(int hourIndex) {
        return getHourlyWeatherDataList().get(hourIndex);
    }

    public String getHourlyDateTime(int hourIndex) {
        if(getHourlyWeatherData(hourIndex).hasDateTime()) {
            return getHourlyWeatherData(hourIndex).getDateTime().toString();
        }
        return null;
    }
    public String getCurrentWeatherIcon() {
        net.aksingh.owmjapis.model.param.Weather currentWeatherData = getCurrentWeatherList().get(0);
        if (currentWeatherData.hasIconLink())
            return currentWeatherData.getIconLink();
        else return null;
    }

    public String getHourlyTemperature(int hourIndex) {
        if(getHourlyWeatherData(hourIndex).getMainData().hasTemp()) {
            return roundTemperature(getHourlyWeatherData(hourIndex).getMainData().getTemp()) + DEGREE_SYMBOL + "C";
        }
        return null;
    }

    public String getHourlyHumidity(int hourIndex) {
        if(getHourlyWeatherData(hourIndex).getMainData().hasHumidity()) {
            return Math.round(getHourlyWeatherData(hourIndex).getMainData().getHumidity()) + " %";
        }
        return null;
    }

    private List<net.aksingh.owmjapis.model.param.Weather> getCurrentWeatherList() {
        if (currentWeather.hasWeatherList())
            return currentWeather.getWeatherList();
        else return null;
    }

    private List<net.aksingh.owmjapis.model.param.Weather> getHourlyWeatherList(int hourIndex) {
        if(getHourlyWeatherData(hourIndex).hasWeatherList()) {
            return getHourlyWeatherData(hourIndex).getWeatherList();
        }
        return null;
    }

    public int getCurrentCondition() {
        net.aksingh.owmjapis.model.param.Weather currentWeatherData = getCurrentWeatherList().get(0);
        return (currentWeatherData.hasConditionId()) ? currentWeatherData.getConditionId(): null;
    }

    public String getHourlyWeatherIcon(int hourIndex)  {
        net.aksingh.owmjapis.model.param.Weather hourlyWeatherData = getHourlyWeatherList(hourIndex).get(0);
        if (hourlyWeatherData.hasIconLink()) {
            return hourlyWeatherData.getIconLink();
        }
        return null;
    }

}
