package weatherStation.model.city;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;


/**
 * Created by "Bartosz Chodyla" on 2020-08-25.
 */
public class CityProvider {

    public List<City> getCityListFromJsonFile(String fileName) {
        InputStream inputStream;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        } catch (NullPointerException e) {
            handleCityFileException();
            return Collections.emptyList();
        }
        if (inputStream == null) {
            handleCityFileException();
            return Collections.emptyList();
        }

        JsonArray jsonArray = JsonParser.parseReader(new InputStreamReader(inputStream)).getAsJsonArray();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<City>>() {
        }.getType();
        List<City> cityList = gson.fromJson(jsonArray, listType);

        return cityList;
    }


    private void handleCityFileException() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Nie udało się wczytać pliku posiadającego listę "
                + "miast. \nNastąpi zamknięcie programu", ButtonType.CLOSE);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.CLOSE) {
            System.exit(0);
        }
    }
}