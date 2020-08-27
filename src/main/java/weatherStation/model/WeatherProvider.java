package weatherStation.model;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import weatherStation.Config;

/**
 * Created by "Bartosz Chodyla" on 2020-08-24.
 */
public class WeatherProvider {

    private OWM owm = new OWM(new Config().getAPI_KEY());
    private DateConverter dateConverter = new DateConverter();
    private CurrentWeather currentWeather;
    private HourlyWeatherForecast hourlyWeatherForecast;
    private final String degreeSymbol = "\u00B0";

    public WeatherProvider(int id) throws APIException {
        owm.setUnit(OWM.Unit.METRIC);
        owm.setLanguage(OWM.Language.POLISH);

        currentWeather = getCurrentWeather(id);
        hourlyWeatherForecast = getHourlyWeather(id);
    }

    private CurrentWeather getCurrentWeather(int cityId) throws APIException {
        CurrentWeather currentWeatherQuery = owm.currentWeatherByCityId(cityId);
        if (currentWeatherQuery.hasRespCode() && currentWeatherQuery.getRespCode() == 200) {
            return currentWeatherQuery;
        } else return null;
    }

    private HourlyWeatherForecast getHourlyWeather(int cityId) throws APIException {
        return owm.hourlyWeatherForecastByCityId(cityId);
    }


    public String getCurrentTemperature() {
        if(currentWeather.getMainData().hasTemp()) {
           return roundTemperature(currentWeather.getMainData().getTemp()) + degreeSymbol + "C";
        }
        return null;
    }

    private Double roundTemperature(Double temp) {
        return Math.round(temp * 10.0) /10.0;
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

    public String getCityName() {
        return (currentWeather.hasCityName()) ? currentWeather.getCityName() : null;
    }

    public String getCountryCode() {
        return (hourlyWeatherForecast.hasCityData()) ? hourlyWeatherForecast.getCityData().getCountryCode() : null;
    }

    public String getCurrentDate() {
        if(currentWeather.hasDateTime()) {
            String currentDate = currentWeather.getDateTime().toString();
            String polishCurrentDate = dateConverter.convertDateToPolish(currentDate);
            return polishCurrentDate;
        }
        return null;
    }

}
