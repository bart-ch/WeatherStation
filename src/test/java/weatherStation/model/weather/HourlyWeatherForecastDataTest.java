package weatherStation.model.weather;

import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by "Bartosz Chodyla" on 2020-09-14.
 */
public class HourlyWeatherForecastDataTest {

    private HourlyWeatherForecast hourlyWeatherForecast;
    private HourlyWeatherForecastData hourlyWeatherForecastData;

    @BeforeEach
    void setUp()  {

        hourlyWeatherForecast = WeatherDataStub.getHourlyWeatherForecast();
        hourlyWeatherForecastData = new HourlyWeatherForecastData(hourlyWeatherForecast);

    }

    @Test
    void shouldBeAbleToGetCityName() {

        //when
        String cityName = hourlyWeatherForecastData.getCityName();

        //then
        assertThat(cityName, equalTo("Warszawa"));
    }

    @Test
    void shouldBeAbleToGetCountryCode() {

        //when
        String countryCode = hourlyWeatherForecastData.getCountryCode();

        //then
        assertThat(countryCode, equalTo("PL"));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,3,5,7})
    void shouldBeAbleToGetHourlyTemperature(int hourIndex) {

        //when
        String hourlyTemperature = hourlyWeatherForecastData.getHourlyTemperature(hourIndex);

        //then
        assertThat(hourlyTemperature, equalTo(20.3 + WeatherOperations.getDegreeSymbol() + "C"));

    }

    @ParameterizedTest
    @ValueSource(ints = {1,3,5,7})
    void shouldBeAbleToGetHourlyHumidity(int hourIndex) {

        //when
        String hourlyHumidity= hourlyWeatherForecastData.getHourlyHumidity(hourIndex);

        //then
        assertThat(hourlyHumidity, equalTo(93 + " %"));

    }

    @ParameterizedTest
    @ValueSource(ints = {1,3,5,7})
    void shouldBeAbleToGetHourlyWeatherIconLink(int hourIndex) {

        //when
        String currentWeatherIconLink = hourlyWeatherForecastData.getHourlyWeatherIcon(hourIndex);

        //then
        assertThat(currentWeatherIconLink, equalTo("https://openweathermap.org/img/w/01d.png"));
    }

}
