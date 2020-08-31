package weatherStation.model;

import java.util.Vector;

/**
 * Created by "Bartosz Chodyla" on 2020-08-27.
 */
public class ForecastHours {

    public String[] getNextHours(WeatherProvider weather) {
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
        return null;
    }

    public Vector<Integer> getIndex(WeatherProvider weatherProvider, String day) {

        String todayDate = weatherProvider.getHourlyWeatherDataList().get(0).getDateTime().toString();
        Vector<Integer> hourIndexes = new Vector<Integer>();
        Vector<Integer> hourIndexesNextDays = new Vector<Integer>();
        for (int i = 0; i < weatherProvider.getHourlyWeatherDataList().size(); i++) {
            String dateToCheck = weatherProvider.getHourlyWeatherDataList().get(i).getDateTime().toString();
            String[] nextHours = getNextHours(weatherProvider);

            if (dateToCheck.substring(11, 13).equals(nextHours[0]) ||
                    dateToCheck.substring(11, 13).equals(nextHours[1]) ||
                    dateToCheck.substring(11, 13).equals(nextHours[2]) ||
                    dateToCheck.substring(11, 13).equals(nextHours[3])) {

                if (dateToCheck.substring(0, 3).equals(todayDate.substring(0, 3))) hourIndexes.add(i);
                else {
                    hourIndexesNextDays.add(i);
                }
            }
        }

        if (day.equals("today")) {
            return hourIndexes;
        } else if (day.equals("nextDay")) {
            return hourIndexesNextDays;
        }
        return null;
    }
}
