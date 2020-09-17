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
        String translatedDate = "Wtorek, 15 września 2020";

        //when
        String result = DateConverter.convertDateToPolish(englishDateTime);

        //then
        assertThat(result, is(translatedDate));
    }

}
