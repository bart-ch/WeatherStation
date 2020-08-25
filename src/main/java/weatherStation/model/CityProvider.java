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

    public CityProvider() throws Exception {
        loadJsonFile();
    }

    private void loadJsonFile() throws Exception {

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("city.list.min.json");

            JsonArray jsonArray = JsonParser.parseReader(new InputStreamReader(inputStream)).getAsJsonArray();

            Gson gson = new Gson();
            Type listType = new TypeToken<List<City>>(){}.getType();
            List<City> cityList = gson.fromJson(jsonArray, listType);

            this.cityList = cityList;

        } catch (Exception ex) {
            throw new Exception();
        }
    }

    public List<City> getCityList() {
        return cityList;
    }
}