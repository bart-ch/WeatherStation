package weatherStation.model.city;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by "Bartosz Chodyla" on 2020-09-14.
 */
public class CityProviderTest {

    @Test
    void shouldBeAbleToLoadCitiesFromValidFile() {

        //given
        CityProvider cityProvider = new CityProvider();
        List<City> citiesList;

        //when
        citiesList = cityProvider.getCityListFromJsonFile("city.list.min.json");

        //then
        assertThat(citiesList.size(), greaterThan(0));
        assertThat(citiesList.get(0).getCountryCode(), notNullValue());
    }

//    @Test
//    void mainWindowShouldNotInitializeIfInvalidFileName() {
//
//        //given
//        CityProvider cityProvider = new CityProvider();
//
//        //when + then
//        assertThrows(ExceptionInInitializerError.class, () -> cityProvider.getCityListFromJsonFile("notexists"));
//    }

}
