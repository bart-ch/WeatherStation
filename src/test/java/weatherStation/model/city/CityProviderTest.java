package weatherStation.model.city;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

/**
 * Created by "Bartosz Chodyla" on 2020-09-14.
 */
public class CityProviderTest {

    CityProvider cityProvider;

    @BeforeEach
    void setUp() {
        cityProvider = new CityProvider();
    }

    @Test
    void shouldBeAbleToLoadCitiesFromValidFile()  {

        //given
        List<City> citiesList;

        //when
        citiesList = cityProvider.getCityListFromJsonFile("city.list.min.json");

        //then
        assertThat(citiesList.size(), greaterThan(0));
    }

}
