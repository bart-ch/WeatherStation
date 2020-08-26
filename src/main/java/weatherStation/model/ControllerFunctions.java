package weatherStation.model;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.aksingh.owmjapis.api.APIException;
import org.controlsfx.control.textfield.TextFields;

import java.util.HashMap;
import java.util.List;

/**
 * Created by "Bartosz Chodyla" on 2020-08-25.
 */
public class ControllerFunctions {

    private TextField currentCity;
    private TextField desiredCity;
    private final String degreeSymbol = "\u00B0";
    private List<City> citiesList;
    private HashMap<String, String> citiesNamesWithCountryCodes;

    public ControllerFunctions(TextField currentCity,TextField desiredCity) {

        this.currentCity = currentCity;
        this.desiredCity = desiredCity;

        try {
            citiesList = new CityProvider().getCityList();
            citiesNamesWithCountryCodes = new HashMap<String, String>();
            int iterator = 0;
            for(int i = 0; i < citiesList.size(); i++) {
                String countryCode = citiesList.get(i).getCountryCode();
                String city = citiesList.get(i).getCityName();
                citiesNamesWithCountryCodes.put(city,city + ", " + countryCode);
            }

            TextFields.bindAutoCompletion(currentCity, citiesNamesWithCountryCodes.values());
            TextFields.bindAutoCompletion(desiredCity, citiesNamesWithCountryCodes.values());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadWeatherForCurrentDay(TextField enteredCity, Label cityName,
                                         Label currentTempForCurrentCity, Label currentDate) {
        String userCity = enteredCity.getText();
        int userCityId = getCityId(userCity);

        try {
            if (userCityId <= 0) {
                throw new Exception();
            } else{
                WeatherProvider todaysWeather = new WeatherProvider(userCityId);
                cityName.setText(todaysWeather.getCityName() + ", " + todaysWeather.getCountryCode());
                //date

                currentTempForCurrentCity.setText(todaysWeather.getCurrentTemperature() + degreeSymbol + "C");
                currentDate.setText(todaysWeather.getCurrentDate());
            }

        } catch (APIException e) {
            e.printStackTrace();
        } catch (Exception e) {
            cityName.setText("Wpisano miasto o błędnym ID.");
        }
    }

    private int getCityId(String userCity) {
        int cityId = 0;
        int iterator = 0;

        String[] splittedArray = null;
        splittedArray = userCity.split(",");
        userCity = splittedArray[0];

        if (citiesNamesWithCountryCodes.containsKey(userCity)) {
            for (City i : citiesList) {
                if (citiesList.get(iterator).getCityName().equals(userCity)) {
                    cityId = citiesList.get(iterator).getCityId();
                }
                iterator++;
            }
        }
        return cityId;
    }
}