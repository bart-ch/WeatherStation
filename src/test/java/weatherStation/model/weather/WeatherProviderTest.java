package weatherStation.model.weather;

import net.aksingh.owmjapis.core.OWM;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by "Bartosz Chodyla" on 2020-09-25.
 */
class WeatherProviderTest {

    @Test
    void shouldReturnCurrentWeather() {
        WeatherProvider weatherProvider = new WeatherProvider();
        OWM owm = mock(OWM.class);
    }


}