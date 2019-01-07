package android.finite.com.timetrack.model.values;

import android.finite.com.timetrack.controller.TimeHandler;
import android.finite.com.timetrack.model.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeModel extends Model<Date> {

    private final SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private TimeZone zone;
    private Date created;

    public TimeModel() {
        this.timeFormat = new SimpleDateFormat("HH:mm");
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.created = Calendar.getInstance().getTime();
        this.value = Calendar.getInstance().getTime();
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

    public String getReadableString(TimeHandler.Type type) {
        switch(type){
            case FROM_TIME:
            case TO_TIME:
                return getTimeString();
            case FROM_DATE:
            case TO_DATE:
                return getDateString();
        }
        return "";
    }

    @Override
    public String getReadableString() {
        return getTimeString();
    }

}
