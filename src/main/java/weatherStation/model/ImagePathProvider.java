package weatherStation.model;

/**
 * Created by "Bartosz Chodyla" on 2020-09-12.
 */
public class ImagePathProvider {

    public static String getBackgroundImagePath(int conditionId, double currentHour, double sunriseHour,
                                                double sunsetHour) {

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

        String imageURL = ControllerFunctions.class.getResource(conditionImage).toExternalForm();

        return imageURL;
    }


}
