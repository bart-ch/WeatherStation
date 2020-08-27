package weatherStation.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.aksingh.owmjapis.api.APIException;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Created by "Bartosz Chodyla" on 2020-08-25.
 */
public class ControllerFunctions {

    private TextField currentCity;
    private TextField desiredCity;

    private List<City> citiesList;
    private HashMap<String, String> citiesNamesWithCountryCodes;
    private ForecastHours forecastHours = new ForecastHours();

    public ControllerFunctions(TextField currentCity,TextField desiredCity) {

        this.currentCity = currentCity;
        this.desiredCity = desiredCity;

        try {
            citiesList = new CityProvider().getCityList();
            citiesNamesWithCountryCodes = new HashMap<String, String>();

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
                                         Label currentTempForCurrentCity, Label currentDate, Label currentCityNow,
                                         Label currentPressure, Label currentHumidity,
                                         HBox currentDayNextHoursWeather, ImageView currentWeatherIcon) {
        String userCity = enteredCity.getText();
        int userCityId = getCityId(userCity);

        try {
            if (userCityId <= 0) {
                throw new Exception();
            } else{
                WeatherProvider weather = new WeatherProvider(userCityId);
                cityName.setText(weather.getCityName() + ", " + weather.getCountryCode());
                currentDate.setText(weather.getCurrentDate());

                currentCityNow.setText("Teraz:");
                currentTempForCurrentCity.setText(weather.getCurrentTemperature());
                currentPressure.setText("Ciśnienie: " + weather.getCurrentPressure());
                currentHumidity.setText("Wilgotność: " + weather.getCurrentHumidity());

                String pathCurrentIconLeft = weather.getCurrentWeatherIcon();
                currentWeatherIcon.setImage(setIcon(pathCurrentIconLeft));

                Vector<Integer> hourIndexes = forecastHours.getIndex(weather, "today");
                Vector<Integer> hourIndexesNextDays = forecastHours.getIndex(weather, "nextDay");

                loadWeatherForCurrentDayForNextHours(currentDayNextHoursWeather, hourIndexes, weather);
               // loadHoursDataForNextDays(nextDaysData, hourIndexesNextDays, weather);

            }

        } catch (APIException e) {
            e.printStackTrace();
        } catch (Exception e) {
            cityName.setText("Wpisano miasto o błędnym ID.");
        }
    }
    private Image setIcon(String path) {
        return new Image(path);
    }

    private void loadWeatherForCurrentDayForNextHours(HBox currentDayNextHoursWeather, Vector<Integer> hourIndexes, WeatherProvider weather) {
        List<VBox> containerForHourData = new ArrayList<>();
        List<Label> selectedHoursForCurrentDay = new ArrayList<>();
        List<ImageView> hourlyIconForCurrentDay = new ArrayList<>();
        List<Label> hourlyTemperatureForCurrentDay = new ArrayList<>();
        List<Label> hourlyHumidityForCurrentDay = new ArrayList<>();

        for (int i = 0; i < hourIndexes.size(); i++) {
            VBox hourWeatherData = new VBox();
            hourWeatherData.setAlignment(Pos.CENTER);
            hourWeatherData.setPrefWidth(110);
            containerForHourData.add(hourWeatherData);

            Label currentDayHour = new Label();
            selectedHoursForCurrentDay.add(currentDayHour);

            Label hourlyTemperatureForCurrentDayLabel = new Label();
            hourlyTemperatureForCurrentDay.add(hourlyTemperatureForCurrentDayLabel);

            Label hourlyHumidityForCurrentDayLabel = new Label();
            hourlyHumidityForCurrentDay.add(hourlyHumidityForCurrentDayLabel);

            ImageView currentTimeWeatherIcon = new ImageView();
            hourlyIconForCurrentDay.add(currentTimeWeatherIcon);
            String pathDayIcon = weather.getHourlyWeatherIcon(hourIndexes.get(i));
            currentTimeWeatherIcon.setImage(setIcon(pathDayIcon));

            hourWeatherData.getChildren().addAll(currentDayHour, hourlyTemperatureForCurrentDayLabel,
                    hourlyHumidityForCurrentDayLabel, currentTimeWeatherIcon);
            currentDayNextHoursWeather.getChildren().add(hourWeatherData);

            currentDayHour.setText(weather.getHourlyDateTime(hourIndexes.get(i)).substring(11, 16));
          //  String pathFirstDayIcon = weather.getHourlyIcon(hourIndexes.get(i));
          //  hourlyIconForCurrentDayImage.setImage(setIcon(pathFirstDayIcon));
            hourlyTemperatureForCurrentDayLabel.setText(weather.getHourlyTemperature(hourIndexes.get(i)));
            hourlyHumidityForCurrentDayLabel.setText(weather.getHourlyHumidity(hourIndexes.get(i)));


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