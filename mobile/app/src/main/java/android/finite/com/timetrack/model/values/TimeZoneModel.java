package android.finite.com.timetrack.model.values;

import android.finite.com.timetrack.model.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimeZone;

public class TimeZoneModel extends Model<TimeZone> {

    public TimeZoneModel() {
        this.value = TimeZone.getDefault();
    }

    public ArrayList<String> getAvailableTimeZones() {
        return new ArrayList<>(Arrays.asList(TimeZone.getAvailableIDs()));
    }

    @Override
    public String getReadableString() {
        return this.value.getID();
    }

    public boolean isTimeZoneValid(String setTmz) {
        return getAvailableTimeZones().contains(setTmz);
    }
}
