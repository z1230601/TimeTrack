package android.finite.com.timetrack.controller;

import android.finite.com.timetrack.data.DataManager;
import android.finite.com.timetrack.db.data.Assignment;
import android.finite.com.timetrack.db.data.TimeEntry;
import android.finite.com.utility.Tuple;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class TimeHandler {

    public TimeHandler() {


    }

    public void saveTimeEntry(String fromDate, String fromTime, String fromTmz,
                              String toDate, String toTime, String toTmz, String type) {
        Log.d("TimeEntry", "Got timeentry to save."
                + fromDate + " " + fromTime + " " + fromTmz + " -> "
                + toDate + " " + toTime + " " + toTmz);
        String from = fromDate + " " + fromTime;
        SimpleDateFormat fromFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        fromFormat.setTimeZone(TimeZone.getTimeZone(fromTmz));
        String to = toDate + " " + toTime;
        SimpleDateFormat toFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        toFormat.setTimeZone(TimeZone.getTimeZone(toTmz));

        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = fromFormat.parse(from);
            dateTo = toFormat.parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        Assignment ass = DataManager.get().getSelectedAssignment();
        TimeEntry entry = new TimeEntry(-1,
                Calendar.getInstance().getTime(),
                dateFrom,
                dateTo,
                TimeEntry.Type.fromName(type),
                ass.getAssignmentId(),
                new ArrayList<Tuple<Date, Date>>());
        DataManager.get().saveNewTimeEntry(entry);
    }

    public static SimpleDateFormat getTimeFormat() {
        return new SimpleDateFormat("HH:mm");
    }

    public static SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("dd.MM.yyyy");
    }

    public List<String> getTypes() {
        return TimeEntry.Type.getList();
    }
}
