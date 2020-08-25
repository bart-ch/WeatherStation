package weatherStation.model;

import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.util.HashMap;
import java.util.List;

/**
 * Created by "Bartosz Chodyla" on 2020-08-25.
 */
public class ControllerFunctions {

    private TextField currentCity;
    private TextField desiredCity;
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
}