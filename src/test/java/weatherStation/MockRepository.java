package weatherStation;

import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.System;
import net.aksingh.owmjapis.model.param.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockRepository {

    public static CurrentWeather getCurrentWeather() {

        Weather weather = getWeather();
        Rain rainData = new Rain();
        Snow snowData = new Snow();
        Coord coord = getCoord();
        List<Weather> list = Collections.singletonList(weather);
        Main mainData = getMainData();
        Cloud cloudData = new Cloud(0.80);
        System systemData = new System(1, 5122, 0.0139, "PL", 1560343627, 1560396563, "pod");
        Wind windData = new Wind(1.5, 50.0, 20.0);
        Integer dateUnixTime = 1600117200;

        return new CurrentWeather(dateUnixTime, rainData, snowData, coord, list, "Warszawa", 200,
                mainData, cloudData, 1851632, systemData, "stations", windData);
    }

    public static WeatherData getWeatherData() {

        Weather weather = getWeather();
        Main mainData = getMainData();
        List<Weather> list = Collections.singletonList(weather);

        return new WeatherData(1,mainData, new Temp(), 1002.0, 49.0, list, new Cloud(),
                new Wind(), new System(), "2014-07-23 09:00:00");
    }

    private static Weather getWeather() {

        return new Weather(800, "Clear", "clear sky", "01d");
    }

    private static Main getMainData() {

        return new Main(20.3, 18.00, 23.00, 1009.00, 1020.00,
                1019.00, 93.00,  10.00);
    }

    private static Coord getCoord() {

        return new Coord(35.00, 139.00);
    }

    public static HourlyWeatherForecast getHourlyWeatherForecast() {

        City cityData = new City(1, "London", getCoord(), "GB", 109876l);
        return new HourlyWeatherForecast("200", 0.0045, cityData, 40, getWeatherDataList());
    }

    private static List<WeatherData> getWeatherDataList() {

        int i = 1;
        List<WeatherData> list = new ArrayList<>();

        while (i <= 40) {
            list.add(getWeatherData());
            i ++;
        }

        return list;
    }
}
