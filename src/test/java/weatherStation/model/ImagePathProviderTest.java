package weatherStation.model;

import net.aksingh.owmjapis.model.CurrentWeather;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import weatherStation.model.weather.CurrentWeatherData;
import weatherStation.model.weather.WeatherDataStub;
import weatherStation.model.date.DateTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;

/**
 * Created by "Bartosz Chodyla" on 2020-09-16.
 */
public class ImagePathProviderTest {

    @ParameterizedTest
    @ValueSource(ints = {200, 400, 600, 800})
    void shouldBeAbleToGetValidBackgroundImageURL(int conditionId) {

        //given
        CurrentWeather currentWeather = WeatherDataStub.getCurrentWeather();
        CurrentWeatherData currentWeatherData = new CurrentWeatherData(currentWeather);
        DateTime dateTime = new DateTime(currentWeatherData);

        //when
        //then
        assertThat(ImagePathProvider.getBackgroundImagePath(dateTime, conditionId), startsWith("/img/"));
        assertThat(ImagePathProvider.getBackgroundImagePath(dateTime, conditionId), endsWith(".jpg"));

    }
}
