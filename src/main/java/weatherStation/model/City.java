package weatherStation.model;

/**
 * Created by "Bartosz Chodyla" on 2020-08-25.
 */
public class City {

    private int id;
    private String country;
    private String city;

    public City(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
