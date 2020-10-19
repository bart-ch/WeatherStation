package weatherStation.model.city;

import java.util.Objects;

/**
 * Created by "Bartosz Chodyla" on 2020-08-25.
 */
public final class City {

    private final int id;
    private final String name;
    private final String country;

    public City(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public int getCityId() {
        return id;
    }

    public String getCityName() {
        return name;
    }

    public String getCountryCode() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id &&
                Objects.equals(name, city.name) &&
                Objects.equals(country, city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
