package weatherStation.model;

/**
 * Created by "Bartosz Chodyla" on 2020-08-26.
 */
public class DateConverter {

    public String convertDateToPolish(String date) {
        String dayName = date.substring(0,3);
        String convertedDayName = convertDateDayName(dayName);
        String monthName = date.substring(4,7);
        String convertedMonthName = convertDateMonthName(monthName);
        String year = date.substring(25);
        String dayOfMonth = date.substring(8, 10);
        int dayNumber = Integer.parseInt(dayOfMonth);
        if (dayNumber < 10) {
            dayOfMonth = dayOfMonth.substring(1);
        }

        String convertedDate = convertedDayName + ", " + dayOfMonth + " " + convertedMonthName + " " + year;
        return convertedDate;
    }

    private String convertDateDayName(String day) {
        switch (day) {
            case "Mon":
                return "Poniedziałek";
            case "Tue":
                return "Wtorek";
            case "Wed":
                return "Środa";
            case "Thu":
                return "Czwartek";
            case "Fri":
                return "Piątek";
            case "Sat":
                return "Sobota";
            case "Sun":
                return "Niedziela";
        }
        return null;
    }

    private String convertDateMonthName(String month) {
        switch(month) {
            case "Jan":
                return "stycznia";
            case "Feb":
                return "lutego";
            case "Mar":
                return "marca";
            case "Apr":
                return "kwietnia";
            case "May":
                return "maja";
            case "Jun":
                return "czerwca";
            case "Jul":
                return "lipca";
            case "Aug":
                return "sierpnia";
            case "Sep":
                return "września";
            case "Oct":
                return "października";
            case "Nov":
                return "listopada";
            case "Dec":
                return "grudnia";
        }
        return null;
    }



}
