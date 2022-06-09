import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtils {

    public static String time;

    public TimeUtils() {
        setNowDate();
    }

    public static void setNowDate() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        time = formatter.format(calendar.getTime());
        System.out.println("Start Log Create at: " + time);
    }

    public static String getActionTime() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat formatter= new SimpleDateFormat(" '|' HH:mm:ss z");
        return formatter.format(calendar.getTime());
    }
}