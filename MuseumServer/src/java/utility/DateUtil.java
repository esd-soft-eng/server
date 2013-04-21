package utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class DateUtil {
    public static String getCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public static String getDateString(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
    
    public static Date subtractWeeksFromCurrentDate(int numWeeks)
    {
        //Standard Java approach to subtracting weeks from date
        Date now = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(now);
        gc.add(Calendar.WEEK_OF_YEAR, -numWeeks);
        return gc.getTime();
    }
}
