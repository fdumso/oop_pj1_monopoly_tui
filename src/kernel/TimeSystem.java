package kernel;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by freemso on 2016/4/26.
 */
public class TimeSystem {
    private SimpleDateFormat format;
    private Calendar calendar;

    public TimeSystem() {
        format = new SimpleDateFormat("yyyy-MM-dd");
        calendar = new GregorianCalendar();
    }

    public String printDate() {
        return format.format(calendar.getTime());
    }

    public void addDay() {
        calendar.add(Calendar.DATE, 1);
    }

    public boolean isEndOfTheMonth() {
        calendar.add(Calendar.DATE, 1);
        boolean isEndOfTheMonth = calendar.get(calendar.DATE) == 1;
        calendar.add(Calendar.DATE, -1);
        return isEndOfTheMonth;
    }


}
