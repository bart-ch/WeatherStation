package weatherStation.model.weather;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by "Bartosz Chodyla" on 2020-09-15.
 */
public class WeatherOperationsTest {

    @ParameterizedTest
    @ValueSource(doubles = {32.344, 32.34, 32.3})
    void shouldReturnRoundedValue(double temperature) {

        //when
        Double roundedTemperature = WeatherOperations.roundTemperature(temperature);

        //then
        assertThat(roundedTemperature, is(32.3));
    }
}
