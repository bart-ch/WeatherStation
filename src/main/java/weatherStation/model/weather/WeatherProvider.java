package weatherStation.model.weather;

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

    public WeatherProvider() {
        owm.setUnit(OWM.Unit.METRIC);
        owm.setLanguage(OWM.Language.POLISH);
    }

    public weatherStation.model.weather.Weather getWeather(int cityId) throws APIException {
        CurrentWeather currentWeatherQuery = owm.currentWeatherByCityId(cityId);
        HourlyWeatherForecast hourlyWeatherForecastQuery = owm.hourlyWeatherForecastByCityId(cityId);

        weatherStation.model.weather.Weather weather =
                new weatherStation.model.weather.Weather(currentWeatherQuery, hourlyWeatherForecastQuery);

        if (currentWeatherQuery.hasRespCode() &&
                currentWeatherQuery.getRespCode() == 200 &&
                hourlyWeatherForecastQuery.hasRespCode()) {
            return weather;
        } else {
            return null;
        }
    }

}
