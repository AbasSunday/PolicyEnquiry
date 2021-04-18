package abas.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtils
{
    /**
     * @return current time stamp eg. 05 March 2019, 15:30
     */
    public static String getTimeStamp()
    {
        return getCurrentDate("dd MMMM yyyy, HH:mm");
    }

    public static String getCurrentDate(String format)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.UK);
        return dateFormat.format(new Timestamp(System.currentTimeMillis()));
    }
}
