package android.finite.com.timetrack.model.values;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Time {
    private final SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private TimeZone zone;
    private Date created;
    private Date value;

    public Time() {
        this.timeFormat = new SimpleDateFormat("HH:mm");
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }

    public void setDayMonthYear() {

    }

    public void setHoursMintues() {

    }

    public String getTimeString() {
        return this.timeFormat.format(this.value);
    }

    public String getDateString() {
        return this.dateFormat.format(this.value);
    }
}
