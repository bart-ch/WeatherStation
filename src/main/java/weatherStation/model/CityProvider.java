package weatherStation.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;


/**
 * Created by "Bartosz Chodyla" on 2020-08-25.
 */
public class CityProvider {
    private List<City> cityList;

    private void loadJsonFile() {

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("city.lisot.min.json");

            JsonArray jsonArray = JsonParser.parseReader(new InputStreamReader(inputStream)).getAsJsonArray();

            Gson gson = new Gson();
            Type listType = new TypeToken<List<City>>(){}.getType();
            List<City> cityList = gson.fromJson(jsonArray, listType);

            this.cityList = cityList;
    }

    public List<City> getCityList() {
        loadJsonFile();
        return cityList;
    }
}