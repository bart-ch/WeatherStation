package weatherStation.model.weather;

/**
 * Created by "Bartosz Chodyla" on 2020-09-13.
 */
public class WeatherOperations {

    private final static String DEGREE_SYMBOL = "\u00B0";

    public static String getDegreeSymbol() {
        return DEGREE_SYMBOL;
    }

    public static Double roundTemperature(Double temp) {
        return Math.round(temp * 10.0) /10.0;
    }

}
