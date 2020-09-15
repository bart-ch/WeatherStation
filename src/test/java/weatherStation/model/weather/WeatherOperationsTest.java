package weatherStation.model.weather;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by "Bartosz Chodyla" on 2020-09-15.
 */
public class WeatherOperationsTest {

    @Test
    void shouldReturnRoundedValue() {
        //given
        Double temperature = 32.345;

        //when
        Double roundedTemperature = WeatherOperations.roundTemperature(temperature);

        //then
        assertThat(roundedTemperature, is(32.3));
    }
}
