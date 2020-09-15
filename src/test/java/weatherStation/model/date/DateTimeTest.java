package weatherStation.model.date;

import net.aksingh.owmjapis.model.CurrentWeather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weatherStation.WeatherDataStub;
import weatherStation.model.weather.CurrentWeatherData;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by "Bartosz Chodyla" on 2020-09-15.
 */
public class DateTimeTest {

    private CurrentWeather currentWeather;
    private CurrentWeatherData currentWeatherData;
    private DateTime dateTime;

    @BeforeEach
    void setUp() {

        currentWeather = WeatherDataStub.getCurrentWeather();
        currentWeatherData = new CurrentWeatherData(currentWeather);
        dateTime = new DateTime(currentWeather);
    }

    @Test
    void shouldBeAbleToGetCurrentTime() {

        //when
        String currentTime = dateTime.getCurrentTime();

        //then
        assertThat(currentTime, is("23.0000"));

    }

    @Test
    void shouldBeAbleToGetSunriseTime() {

        //when
        String currentTime = dateTime.getSunriseTime();

        //then
        assertThat(currentTime, is("08.0000"));
    }

    @Test
    void shouldBeAbleToGetSunsetTime() {

        //when
        String currentTime = dateTime.getSunsetTime();

        //then
        assertThat(currentTime, is("22.2320"));
    }

}
