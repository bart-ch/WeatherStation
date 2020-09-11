package weatherStation.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.aksingh.owmjapis.api.APIException;
import org.controlsfx.control.textfield.TextFields;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Created by "Bartosz Chodyla" on 2020-08-25.
 */
public class ControllerFunctions {

    private List<City> citiesList;
    private CityProvider cityProvider;
    private Map<String, String> citiesNamesWithCountryCodes;
    private ForecastHours forecastHours = new ForecastHours();

    public ControllerFunctions() {
        cityProvider = new CityProvider();
    }

    public final void init(TextField currentCity, TextField desiredCity) {
        citiesList = cityProvider.getCityList();
        enableAutoCompletionOfCityTextFields(currentCity, desiredCity);
    }

    private void enableAutoCompletionOfCityTextFields(TextField currentCity, TextField desiredCity) {

        citiesNamesWithCountryCodes = new HashMap<>();

        for (City city : citiesList) {
            String countryCode = city.getCountryCode();
            String cityName = city.getCityName();
            citiesNamesWithCountryCodes.put(cityName, cityName + ", " + countryCode);
        }

        TextFields.bindAutoCompletion(currentCity, citiesNamesWithCountryCodes.values());
        TextFields.bindAutoCompletion(desiredCity, citiesNamesWithCountryCodes.values());
    }



    public void loadWeather(TextField enteredCity, Label cityName,
                            Label currentTempForCurrentCity, Label currentDate, Label currentCityNow,
                            Label currentPressure, Label currentHumidity,
                            HBox currentDayNextHoursWeather, ImageView currentWeatherIcon,
                            GridPane weatherForNextDays, GridPane weatherBackground) {

        deletePreviousWeatherData(cityName, currentTempForCurrentCity, currentDate, currentCityNow,
                currentPressure, currentHumidity, currentDayNextHoursWeather, currentWeatherIcon, weatherForNextDays, weatherBackground);

        String userCity = enteredCity.getText();
        int userCityId = getCityId(userCity);

        try {
            if (userCityId <= 0) {
                throw new Exception();
            } else {
                WeatherProvider weather = new WeatherProvider(userCityId);
                cityName.setText(weather.getCityName() + ", " + weather.getCountryCode());
                currentDate.setText(weather.getCurrentDate());

                currentCityNow.setText("Teraz:");
                currentTempForCurrentCity.setText(weather.getCurrentTemperature());
                currentPressure.setText("Ciśnienie: " + weather.getCurrentPressure());
                currentHumidity.setText("Wilgotność: " + weather.getCurrentHumidity());

                String pathCurrentIconLeft = weather.getCurrentWeatherIcon();
                currentWeatherIcon.setImage(setIcon(pathCurrentIconLeft));

                String imageURL = getUrlOfBackgroundImage(weather.getCurrentCondition(),
                        weather.getCurrentDateTime(), weather.getSunriseDateTime(),
                        weather.getSunsetDateTime());
                weatherBackground.setStyle("-fx-background-image: url('" + imageURL + "'); -fx-background-position: center; " +
                        "-fx-background-size: cover;");

                Vector<Integer> hourIndexes = forecastHours.getIndex(weather, "today");
                Vector<Integer> hourIndexesNextDays = forecastHours.getIndex(weather, "nextDay");

                loadWeatherForCurrentDayForNextHours(currentDayNextHoursWeather, hourIndexes, weather);
                loadHoursDataForNextDays(weatherForNextDays, hourIndexesNextDays, weather);
            }

        } catch (APIException e) {
            cityName.setText("Niepoprawne dane.");

        } catch (UnknownHostException ex) {
            cityName.setText("Brak połączenia z siecią.");

        } catch (NoRouteToHostException exc) {
            cityName.setText("Przerwano połączenie z siecią.");

        } catch (SocketTimeoutException exce) {
            cityName.setText("Serwer nie odpowiada.");

        } catch (Exception excep) {
            cityName.setText("Brak danych.");
        }
    }

    private void deletePreviousWeatherData(Label cityName,
                                           Label currentTempForCurrentCity, Label currentDate, Label currentCityNow,
                                           Label currentPressure, Label currentHumidity,
                                           HBox currentDayNextHoursWeather, ImageView currentWeatherIcon,
                                           GridPane weatherForNextDays, GridPane weatherBackground) {
        cityName.setText(null);
        currentTempForCurrentCity.setText(null);
        currentDate.setText(null);
        currentCityNow.setText(null);
        currentPressure.setText(null);
        currentHumidity.setText(null);
        currentDayNextHoursWeather.getChildren().clear();
        currentWeatherIcon.setImage(null);
        weatherForNextDays.getChildren().clear();
        weatherBackground.setStyle(null);
    }


    private static String getUrlOfBackgroundImage(int conditionId, String currentDateTime, String sunriseDateTime, String sunsetDateTime) {

        double sunriseHour = Double.parseDouble(sunriseDateTime.substring(11, 13) + "." + sunriseDateTime.substring(14,
                16) + sunriseDateTime.substring(17, 19));
        double sunsetHour = Double.parseDouble(sunsetDateTime.substring(11, 13) + "." + sunsetDateTime.substring(14,
                16) + sunsetDateTime.substring(17, 19));
        double currentHour = Double.parseDouble(currentDateTime.substring(11, 13) + "." + currentDateTime.substring(14,
                16) + currentDateTime.substring(17, 19));

        String conditionImage;

        if ((currentHour > sunriseHour) && (currentHour < sunsetHour)) {
            if ((conditionId >= 200) && (conditionId <= 232)) {
                conditionImage = "/img/thunderstorm_day.jpg";
            } else if ((conditionId >= 300) && (conditionId <= 531)) {
                conditionImage = "/img/rain_day.jpg";
            } else if ((conditionId >= 600) && (conditionId <= 622)) {
                conditionImage = "/img/snow_day.jpg";
            } else if ((conditionId >= 701) && (conditionId <= 781)) {
                conditionImage = "/img/fog_day.jpg";
            } else if ((conditionId >= 801) && (conditionId <= 804)) {
                conditionImage = "/img/clouds_day.jpg";
            } else if ((conditionId == 800)) {
                conditionImage = "/img/sun.jpg";
            } else conditionImage = "";
        } else {
            if ((conditionId >= 200) && (conditionId <= 232)) {
                conditionImage = "/img/thunderstorm_night.jpg";
            } else if ((conditionId >= 300) && (conditionId <= 531)) {
                conditionImage = "/img/rain_night.jpg";
            } else if ((conditionId >= 600) && (conditionId <= 622)) {
                conditionImage = "/img/snow_night.jpg";
            } else if ((conditionId >= 701) && (conditionId <= 781)) {
                conditionImage = "/img/fog_night.jpg";
            } else if ((conditionId >= 801) && (conditionId <= 804)) {
                conditionImage = "/img/clouds_night.jpg";
            } else if ((conditionId == 800)) {
                conditionImage = "/img/moon.jpg";
            } else conditionImage = "";
        }

        return ControllerFunctions.class.getResource(conditionImage).toExternalForm();
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

            hourWeatherData.getChildren().addAll(currentDayHour, currentTimeWeatherIcon,
                    hourlyTemperatureForCurrentDayLabel,
                    hourlyHumidityForCurrentDayLabel);
            currentDayNextHoursWeather.getChildren().add(hourWeatherData);

            currentDayHour.setText(weather.getHourlyDateTime(hourIndexes.get(i)).substring(11, 16));
            currentDayHour.getStyleClass().add("label-text");

            hourlyTemperatureForCurrentDayLabel.setText(weather.getHourlyTemperature(hourIndexes.get(i)));
            hourlyTemperatureForCurrentDayLabel.getStyleClass().add("label-text");

            hourlyHumidityForCurrentDayLabel.setText(weather.getHourlyHumidity(hourIndexes.get(i)));
            hourlyHumidityForCurrentDayLabel.getStyleClass().add("label-text");

        }
    }

    private void loadHoursDataForNextDays(GridPane currentCityNextDaysWeather, Vector<Integer> hourIndexes,
                                          WeatherProvider weather) {
        List<VBox> nextDays = new ArrayList<>();
        List<Label> nextDaysDate = new ArrayList<>();
        List<HBox> oneDayDataList = new ArrayList<>();
        List<VBox> hoursData = new ArrayList<>();
        List<Label> selectedHours = new ArrayList<>();
        List<ImageView> hourlyIcons = new ArrayList<>();
        List<Label> hourlyTemperatureData = new ArrayList<>();
        List<Label> hourlyHumidityData = new ArrayList<>();
        List<Separator> separators = new ArrayList<>();

        int index = 1;

        for (int i = 1; i <= 4; i++) {

            VBox nextDay = new VBox();
            nextDay.setAlignment(Pos.CENTER);
            nextDay.setSpacing(5);
            nextDays.add(nextDay);

            Label dayDate = new Label();
            nextDaysDate.add(dayDate);

            HBox oneDayData = new HBox();
            oneDayData.setAlignment(Pos.CENTER);

            oneDayDataList.add(oneDayData);

            for (int j = 0; j < 4; j++) {
                VBox hourData = new VBox();
                hourData.setAlignment(Pos.CENTER);
                hourData.setPrefWidth(110);
                hourData.setSpacing(5);
                hoursData.add(hourData);

                Label selectedHour = new Label();
                selectedHours.add(selectedHour);

                ImageView hourlyIcon = new ImageView();
                hourlyIcons.add(hourlyIcon);

                Label hourlyTemperature = new Label();
                hourlyTemperatureData.add(hourlyTemperature);

                Label hourlyHumidity = new Label();
                hourlyHumidityData.add(hourlyHumidity);

                Separator separator = new Separator();
                separator.setOpacity(0.5);
                separator.prefWidth(200.0);
                separators.add(separator);

                hourData.getChildren().addAll(selectedHour, hourlyIcon, hourlyTemperature, hourlyHumidity, separator);
                oneDayData.getChildren().add(hourData);

                if (j == 0) {
                    String date = DateConverter.convertDateToPolish(weather.getHourlyDateTime(hourIndexes.get(0)));
                    dayDate.setText(date);
                    dayDate.getStyleClass().add("label-text");
                    dayDate.setStyle("-fx-font: 14 System; -fx-padding: 10px;");
                }

                selectedHour.setText(weather.getHourlyDateTime(hourIndexes.get(0)).substring(11, 16));
                selectedHour.getStyleClass().add("label-text");

                String pathDayIcon = weather.getHourlyWeatherIcon(hourIndexes.get(0));
                hourlyIcon.setImage(setIcon(pathDayIcon));

                hourlyTemperature.setText(weather.getHourlyTemperature(hourIndexes.get(0)));
                hourlyTemperature.getStyleClass().add("label-text");

                hourlyHumidity.setText(weather.getHourlyHumidity(hourIndexes.get(0)));
                hourlyHumidity.getStyleClass().add("label-text");

                hourIndexes.remove(0);
            }
            nextDay.getChildren().add(dayDate);
            nextDay.getChildren().add(oneDayData);
            currentCityNextDaysWeather.add(nextDay, 0, index);
            currentCityNextDaysWeather.setAlignment(Pos.CENTER);
            index++;

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