package weatherStation.model.date;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by "Bartosz Chodyla" on 2020-09-14.
 */
public class DateConverterTest {

    @Test
    void shouldReturnTranslatedDate() {

        //given
        String englishDateTime = "Tue Sep 15 05:00:00 CEST 2020";
        String expected = "Wtorek, 15 września 2020";

        //when
        String result = DateConverter.convertDateToPolish(englishDateTime);

        //then
        assertThat(result, is(expected));
    }

    @Test
    void shouldReturnTranslatedDateWhenStartsWithWhiteSpace() {

        //given
        String englishDateTime = " Tue Sep 15 05:00:00 CEST 2020";
        String expected = "Wtorek, 15 września 2020";

        //when
        String result = DateConverter.convertDateToPolish(englishDateTime);

        //then
        assertThat(result, is(expected));
    }

    @Test
    void shouldReturnErrorMessageIfDateIsEmpty() {

        //given
        String englishDateTime = "";
        String expected = "Błąd podczas pobierania daty.";

        //when
        String result = DateConverter.convertDateToPolish(englishDateTime);

        //then
        assertThat(result, is(expected));
    }

}
