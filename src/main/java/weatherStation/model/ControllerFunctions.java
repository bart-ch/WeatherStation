package weatherStation.model;

import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

/**
 * Created by "Bartosz Chodyla" on 2020-08-25.
 */
public class ControllerFunctions {

    private TextField currentCountry;
    private TextField currentCity;
    private TextField desiredCountry;
    private TextField desiredCity;
    private List<City> citiesList;
    private HashMap<String, String> citiesNamesWithCountryCodes;

    public ControllerFunctions(TextField currentCountry, TextField currentCity, TextField desiredCountry, TextField desiredCity) {
        this.currentCountry = currentCountry;
        this.currentCity = currentCity;
        this.desiredCountry = desiredCountry;
        this.desiredCity = desiredCity;

        try {
            citiesList = new CityProvider().getCityList();
            citiesNamesWithCountryCodes = new HashMap<String, String>();
            int iterator = 0;
            for(int i = 0; i<citiesList.size(); i++) {
                String countryCode = citiesList.get(i).getCountryCode();
                String city = citiesList.get(i).getCityName();
                citiesNamesWithCountryCodes.put(city,city + ", " + countryCode);
            }

            TextFields.bindAutoCompletion(currentCity, citiesNamesWithCountryCodes.values());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
