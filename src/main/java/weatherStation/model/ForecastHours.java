package weatherStation.model;

import weatherStation.model.weather.HourlyWeatherForecastData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by "Bartosz Chodyla" on 2020-08-27.
 */
public class ForecastHours {

    private String[] getNextHours(HourlyWeatherForecastData weather) {

        for (int i = 0; i < weather.getHourlyWeatherDataList().size(); i++) {
            String date = weather.getHourlyWeatherDataList().get(i).getDateTime().toString();
            if (date.substring(11, 13).equals("04")) {
                String[] nextHours = {"04", "10", "16", "22"};
                return nextHours;
            } else if (date.substring(11, 13).equals("05")) {
                String[] nextHours = {"05", "11", "17", "23"};
                return nextHours;
            }
        }

        return new String[]{};
    }

    public List<Integer> getIndex(HourlyWeatherForecastData weather, String day) {

        String todayDate = weather.getHourlyWeatherDataList().get(0).getDateTime().toString();
        List<Integer> hourIndexes = new ArrayList<>();
        List<Integer> hourIndexesNextDays = new ArrayList<>();
        for (int i = 0; i < weather.getHourlyWeatherDataList().size(); i++) {
            String dateToCheck = weather.getHourlyWeatherDataList().get(i).getDateTime().toString();
            String[] nextHours = getNextHours(weather);

            if(nextHours.length == 0) {
                return Collections.emptyList();
            }

            String forecastHours = dateToCheck.substring(11, 13);

            if (forecastHours.equals(nextHours[0]) ||
                    forecastHours.equals(nextHours[1]) ||
                    forecastHours.equals(nextHours[2]) ||
                    forecastHours.equals(nextHours[3])) {

                if (dateToCheck.substring(0, 3).equals(todayDate.substring(0, 3))) {
                    hourIndexes.add(i);
                } else {
                    hourIndexesNextDays.add(i);
                }
            }
        }

        if (day.equals("today")) {
            return hourIndexes;
        } else if (day.equals("nextDay")) {
            return hourIndexesNextDays;
        }
        return Collections.emptyList();
    }
}
