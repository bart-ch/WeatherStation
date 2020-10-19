package weatherStation.model.weather;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;

import java.net.UnknownHostException;

/**
 * Created by "Bartosz Chodyla" on 2020-08-24.
 */
public class WeatherProvider {

    private OWM owm;

    public WeatherProvider(OWM owm) {
        this.owm = owm;
    }

    public CurrentWeatherData getCurrentWeather(int cityId) throws APIException, UnknownHostException {

        CurrentWeatherData currentWeather = new CurrentWeatherData(owm.currentWeatherByCityId(cityId));

        return currentWeather;

    }

    public HourlyWeatherForecastData getHourlyWeather(int cityId) throws APIException, UnknownHostException {

        HourlyWeatherForecastData hourlyWeather = new HourlyWeatherForecastData(owm.hourlyWeatherForecastByCityId(cityId));

        return hourlyWeather;

    }

}
