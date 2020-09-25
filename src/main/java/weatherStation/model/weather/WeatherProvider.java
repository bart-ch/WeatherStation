package weatherStation.model.weather;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import weatherStation.Config;

import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by "Bartosz Chodyla" on 2020-08-24.
 */
public class WeatherProvider {

    private OWM owm = new OWM(new Config().getAPI_KEY());

    public WeatherProvider() {
        owm.setUnit(OWM.Unit.METRIC);
        owm.setLanguage(OWM.Language.POLISH);
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
