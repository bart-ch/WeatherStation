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
import weatherStation.model.city.City;
import weatherStation.model.city.CityProvider;
import weatherStation.model.date.DateConverter;
import weatherStation.model.weather.Weather;
import weatherStation.model.weather.WeatherProvider;

import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by "Bartosz Chodyla" on 2020-08-25.
 */
public class ControllerFunctions {

    private List<City> citiesList;
    private Map<String, String> citiesNamesWithCountryCodes;

    public final void init(TextField currentCity, TextField desiredCity) {
        CityProvider cityProvider = new CityProvider();
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
            }
            WeatherProvider weatherProvider = new WeatherProvider();
            Weather weather = weatherProvider.getWeather(userCityId);


            cityName.setText(weather.getCityName() + ", " + weather.getCountryCode());
            currentDate.setText(weather.getCurrentDate());

            currentCityNow.setText(Messages.NOW);
            currentTempForCurrentCity.setText(weather.getCurrentTemperature());
            currentPressure.setText(Messages.PRESSURE + weather.getCurrentPressure());
            currentHumidity.setText(Messages.HUMIDITY + weather.getCurrentHumidity());

            String currentWeatherIconPath = weather.getCurrentWeatherIcon();
            currentWeatherIcon.setImage(setIcon(currentWeatherIconPath));

            setBackgroundImage(weather, weatherBackground);

            ForecastHours forecastHours = new ForecastHours();
            List<Integer> hourIndexes = forecastHours.getIndex(weather, "today");
            List<Integer> hourIndexesNextDays = forecastHours.getIndex(weather, "nextDay");

            loadWeatherForCurrentDayForNextHours(currentDayNextHoursWeather, hourIndexes, weather);
            loadHoursDataForNextDays(weatherForNextDays, hourIndexesNextDays, weather);


        } catch (APIException e) {
            cityName.setText("Niepoprawne dane.");

        } catch (UnknownHostException ex) {
            cityName.setText("Brak połączenia z siecią.");

        } catch (NoRouteToHostException exc) {
            cityName.setText("Przerwano połączenie z siecią.");

        } catch (SocketTimeoutException exce) {
            cityName.setText("Serwer nie odpowiada.");

        } catch (Exception excep) {
            cityName.setText("Brak danych o podanym mieście.");
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


    private void setBackgroundImage(Weather weather, GridPane weatherBackground) {

        int conditionId = weather.getCurrentCondition();
        String currentDateTime = weather.getCurrentDateTime();
        String sunriseDateTime = weather.getSunriseDateTime();
        String sunsetDateTime = weather.getSunsetDateTime();

        double sunriseHour = Double.parseDouble(sunriseDateTime.substring(11, 13) + "." + sunriseDateTime.substring(14,
                16) + sunriseDateTime.substring(17, 19));
        double sunsetHour = Double.parseDouble(sunsetDateTime.substring(11, 13) + "." + sunsetDateTime.substring(14,
                16) + sunsetDateTime.substring(17, 19));
        double currentHour = Double.parseDouble(currentDateTime.substring(11, 13) + "." + currentDateTime.substring(14,
                16) + currentDateTime.substring(17, 19));

        String imageURL = ImagePathProvider.getBackgroundImagePath(conditionId, currentHour, sunriseHour, sunsetHour);

        weatherBackground.setStyle("-fx-background-image: url('" + imageURL + "'); -fx-background-position: center; " +
                "-fx-background-size: cover;");

    }

    private Image setIcon(String path) {
        return new Image(path);
    }

    private void loadWeatherForCurrentDayForNextHours(HBox currentDayNextHoursWeather, List<Integer> hourIndexes,
                                                      Weather weather) {

        for (Integer hourIndex : hourIndexes) {
            VBox hourWeatherData = new VBox();
            hourWeatherData.setAlignment(Pos.CENTER);
            hourWeatherData.setPrefWidth(110);

            Label currentDayHour = new Label();

            Label hourlyTemperatureForCurrentDayLabel = new Label();

            Label hourlyHumidityForCurrentDayLabel = new Label();

            ImageView currentTimeWeatherIcon = new ImageView();
            String pathDayIcon = weather.getHourlyWeatherIcon(hourIndex);
            currentTimeWeatherIcon.setImage(setIcon(pathDayIcon));

            hourWeatherData.getChildren().addAll(currentDayHour, currentTimeWeatherIcon,
                    hourlyTemperatureForCurrentDayLabel,
                    hourlyHumidityForCurrentDayLabel);
            currentDayNextHoursWeather.getChildren().add(hourWeatherData);

            currentDayHour.setText(weather.getHourlyDateTime(hourIndex).substring(11, 16));
            currentDayHour.getStyleClass().add("label-text");

            hourlyTemperatureForCurrentDayLabel.setText(weather.getHourlyTemperature(hourIndex));
            hourlyTemperatureForCurrentDayLabel.getStyleClass().add("label-text");

            hourlyHumidityForCurrentDayLabel.setText(weather.getHourlyHumidity(hourIndex));
            hourlyHumidityForCurrentDayLabel.getStyleClass().add("label-text");

        }
    }

    private void loadHoursDataForNextDays(GridPane currentCityNextDaysWeather, List<Integer> hourIndexes,
                                          Weather weather) {

        int index = 1;
        final int NUMBER_OF_FORECASTING_NEXT_DAYS = 4;
        final int NUMBER_OF_FORECASTING_TIMES_PER_DAY = 4;

        for (int i = 1; i <= NUMBER_OF_FORECASTING_NEXT_DAYS; i++) {

            VBox nextDay = new VBox();
            nextDay.setAlignment(Pos.CENTER);
            nextDay.setSpacing(5);

            Label dayDate = new Label();

            HBox oneDayData = new HBox();
            oneDayData.setAlignment(Pos.CENTER);

            for (int j = 0; j < NUMBER_OF_FORECASTING_TIMES_PER_DAY; j++) {
                VBox hourData = new VBox();
                hourData.setAlignment(Pos.CENTER);
                hourData.setPrefWidth(110);
                hourData.setSpacing(5);

                Label selectedHour = new Label();
                ImageView hourlyIcon = new ImageView();
                Label hourlyTemperature = new Label();
                Label hourlyHumidity = new Label();

                Separator separator = new Separator();
                separator.setOpacity(0.5);
                separator.prefWidth(200.0);

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

    private int getCityId(String givenCity) {

        String userCity = givenCity.substring(0, givenCity.indexOf(","));

        if (citiesNamesWithCountryCodes.containsKey(userCity)) {
            for (City city : citiesList) {
                if (city.getCityName().equals(userCity)) {
                    return city.getCityId();
                }
            }
        }
        return 0;
    }
}