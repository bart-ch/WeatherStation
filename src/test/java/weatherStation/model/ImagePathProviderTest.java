package weatherStation.model;

import net.aksingh.owmjapis.model.CurrentWeather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import weatherStation.WeatherDataStub;
import weatherStation.model.ImagePathProvider;
import weatherStation.model.date.DateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * Created by "Bartosz Chodyla" on 2020-09-16.
 */
public class ImagePathProviderTest {

    @ParameterizedTest
    @ValueSource(ints = {200, 400, 600, 800})
    void shouldBeAbleToGetValidBackgroundImageURL(int conditionId) {

        //given
        CurrentWeather currentWeather = WeatherDataStub.getCurrentWeather();
        DateTime dateTime = new DateTime(currentWeather);

        //then
        assertThat(ImagePathProvider.getBackgroundImagePath(dateTime, conditionId), startsWith("/img/"));
        assertThat(ImagePathProvider.getBackgroundImagePath(dateTime, conditionId), endsWith(".jpg"));

    }
}
