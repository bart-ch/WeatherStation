package weatherStation.model.weather;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weatherStation.MockRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by "Bartosz Chodyla" on 2020-09-14.
 */
public class CurrentWeatherDataTest {

    private CurrentWeather currentWeather;
    private WeatherProvider weatherProvider;
    private CurrentWeatherData currentWeatherData;

    @BeforeEach
    void setUp() throws APIException {

        currentWeather = MockRepository.getCurrentWeather();
        weatherProvider = mock(WeatherProvider.class);
        currentWeatherData = new CurrentWeatherData(currentWeather);
        given(weatherProvider.getCurrentWeather(10)).willReturn(currentWeatherData);
    }

    @Test
    void shouldBeAbleToGetCurrentWeatherDate() {

        //when
        String resultDate = currentWeatherData.getCurrentDate();

        //then
        assertThat(resultDate, equalTo("Poniedziałek, 14 września 2020"));
    }

    @Test
    void shouldBeAbleToGetCurrentTemperature() {

        //when
        String currentTemperature = currentWeatherData.getCurrentTemperature();

        //then
        assertThat(currentTemperature, equalTo(20.3 + WeatherOperations.getDegreeSymbol() + "C"));
    }

    @Test
    void shouldBeAbleToGetCurrentHumidity() {

        //when
        String currentHumidity = currentWeatherData.getCurrentHumidity();

        //then
        assertThat(currentHumidity, equalTo(93 + " %"));
    }

    @Test
    void shouldBeAbleToGetCurrentPressure() {

        //when
        String currentPressure = currentWeatherData.getCurrentPressure();

        //then
        assertThat(currentPressure, equalTo(1009 + " hPa"));
    }

    @Test
    void shouldBeAbleToGetCurrentCondition() {

        //when
        int conditionId = currentWeatherData.getCurrentCondition();

        //then
        assertThat(conditionId, equalTo(800));
    }

    @Test
    void shouldBeAbleToGetCurrentWeatherIconLink() {

        //when
        String currentWeatherIconLink = currentWeatherData.getCurrentWeatherIconLink();

        //then
        assertThat(currentWeatherIconLink, equalTo("https://openweathermap.org/img/w/01d.png"));
    }


}
