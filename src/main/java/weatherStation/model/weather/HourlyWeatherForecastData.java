package weatherStation.model.weather;

import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.Weather;
import net.aksingh.owmjapis.model.param.WeatherData;

import java.util.List;

/**
 * Created by "Bartosz Chodyla" on 2020-09-13.
 */
public class HourlyWeatherForecastData {

    private HourlyWeatherForecast hourlyWeatherForecast;

    public HourlyWeatherForecastData(HourlyWeatherForecast hourlyWeatherForecast) {
        this.hourlyWeatherForecast = hourlyWeatherForecast;
    }

    public String getCityName() {
        return (hourlyWeatherForecast.hasCityData()) ? hourlyWeatherForecast.getCityData().getName() : null;
    }

    public String getCountryCode() {
        return (hourlyWeatherForecast.hasCityData()) ? hourlyWeatherForecast.getCityData().getCountryCode() : null;
    }

    public List<WeatherData> getHourlyWeatherDataList() {
        return (hourlyWeatherForecast.hasDataList()) ? hourlyWeatherForecast.getDataList() : null;
    }

    private WeatherData getHourlyWeatherData(int hourIndex) {
        return getHourlyWeatherDataList().get(hourIndex);
    }

    public String getHourlyDateTime(int hourIndex) {
        if (getHourlyWeatherData(hourIndex).hasDateTime()) {
            return getHourlyWeatherData(hourIndex).getDateTime().toString();
        }
        return null;
    }

    public String getHourlyTemperature(int hourIndex) {
        if (getHourlyWeatherData(hourIndex).getMainData().hasTemp()) {
            return WeatherOperations.roundTemperature(getHourlyWeatherData(hourIndex).getMainData().getTemp())
                    + WeatherOperations.getDegreeSymbol() + "C";
        }
        return null;
    }

    public String getHourlyHumidity(int hourIndex) {
        if (getHourlyWeatherData(hourIndex).getMainData().hasHumidity()) {
            return Math.round(getHourlyWeatherData(hourIndex).getMainData().getHumidity()) + " %";
        }
        return null;
    }

    private List<Weather> getHourlyWeatherList(int hourIndex) {
        if (getHourlyWeatherData(hourIndex).hasWeatherList()) {
            return getHourlyWeatherData(hourIndex).getWeatherList();
        }
        return null;
    }

    public String getHourlyWeatherIcon(int hourIndex) {
        Weather hourlyWeatherData = getHourlyWeatherList(hourIndex).get(0);
        if (hourlyWeatherData.hasIconLink()) {
            return hourlyWeatherData.getIconLink();
        }
        return null;
    }

}
