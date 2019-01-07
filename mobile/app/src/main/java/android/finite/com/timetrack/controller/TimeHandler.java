package android.finite.com.timetrack.controller;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.Resources;
import android.finite.com.timetrack.R;
import android.finite.com.timetrack.Settings;
import android.finite.com.timetrack.TimeAddActivity;
import android.finite.com.timetrack.data.DataManager;
import android.finite.com.timetrack.db.data.Assignment;
import android.finite.com.timetrack.db.data.TimeEntry;
import android.finite.com.timetrack.model.Model;
import android.finite.com.timetrack.model.values.TimeModel;
import android.finite.com.timetrack.model.values.TimeZoneModel;
import android.finite.com.timetrack.model.values.TypeModel;
import android.finite.com.timetrack.services.TimeCheckService;
import android.finite.com.timetrack.view.wrapper.ViewWrapper;
import android.finite.com.utility.Tuple;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


public class TimeHandler implements Controller<Map<TimeHandler.Type, Model<?>>> {
    private static final String TAG = "TimeHandler";
    private static final String CHANNEL_ID = "Time Channel";
    private static final int NOTIFICATION_ID = 1;
    private boolean isTrackingActive = false;
    private Activity currentRequestor = null;

    public enum Type {
        FROM_TIME,
        FROM_DATE,
        FROM_TIMEZONE,
        TO_TIME,
        TO_DATE,
        TO_TIMEZONE,
        TYPE
    }

    private Map<Type, Model<?>> value = new HashMap<>();

    public TimeHandler() {
        TimeModel fromTimeModel = new TimeModel();
        TimeModel toTimeModel = new TimeModel();
        TimeZoneModel fromTimeZone = new TimeZoneModel();
        TimeZoneModel toTimeZone = new TimeZoneModel();
        TypeModel typeModel = new TypeModel();

        this.value.put(Type.FROM_DATE, fromTimeModel);
        this.value.put(Type.FROM_TIME, fromTimeModel);
        this.value.put(Type.FROM_TIMEZONE, fromTimeZone);

        this.value.put(Type.TO_DATE, toTimeModel);
        this.value.put(Type.TO_TIME, toTimeModel);
        this.value.put(Type.TO_TIMEZONE, toTimeZone);
        this.value.put(Type.TYPE, typeModel);
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

    public List<String> getTypes() {
        return TimeEntry.Type.getList();
    }

    public TimeModel getTime(Type type) {
        if(this.value.containsKey(type) && this.value.get(type) instanceof TimeModel) {
            return (TimeModel) this.value.get(type);
        }
        Log.d("TimeHandler", "getTime: TimeModel " +  type + " not in values!");
        return null;
    }

    public TimeZoneModel getTimeZone(Type type) {
        if(this.value.containsKey(type) && this.value.get(type) instanceof TimeZoneModel) {
            return (TimeZoneModel) this.value.get(type);
        }
        return null;
    }

    public TypeModel getType() {
        return (TypeModel) this.value.get(Type.TYPE);
    }
    @Override
    public void registerToAllModels(ViewWrapper viewWrapper) {
        for(Map.Entry<Type, Model<?>> entry : this.value.entrySet()){
            entry.getValue().registerModel(viewWrapper);
        }
    }

    @Override
    public Map<Type, Model<?>> getModel() {
        return this.value;
    }

    public boolean timeAquisitionOngoing() {
        return this.isTrackingActive;
    }

    public void startTracking(Activity requestor) {
        this.currentRequestor = requestor;
        this.isTrackingActive = true;
        ((TimeModel) this.value.get(Type.FROM_DATE)).setValue(new GregorianCalendar().getTime());
        ((TimeModel) this.value.get(Type.FROM_TIME)).setValue(new GregorianCalendar().getTime());
        // get default should return the current active timezone
        ((TimeZoneModel) this.value.get(Type.FROM_TIMEZONE)).setValue(TimeZone.getDefault());
        if(requestor != null) {
            Intent intent = new Intent();
            intent.setClass(requestor, TimeCheckService.class);
            requestor.startService(intent);
        }
    }

    public void stopTracking(Activity requestor) {
        this.isTrackingActive = false;
        ((TimeModel) this.value.get(Type.TO_DATE)).setValue(new GregorianCalendar().getTime());
        ((TimeModel) this.value.get(Type.TO_TIME)).setValue(new GregorianCalendar().getTime());
        // get default should return the current active timezone
        ((TimeZoneModel) this.value.get(Type.TO_TIMEZONE)).setValue(TimeZone.getDefault());

        if(requestor != null) {
            Intent intent = new Intent();
            intent.setClass(requestor, TimeAddActivity.class);
            requestor.startActivity(intent);
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this.currentRequestor);
        notificationManager.cancel(NOTIFICATION_ID);
    }


    public void updateNotification() {
        if(this.currentRequestor == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = this.currentRequestor.getString(R.string.notification_channel_name);
            String description = this.currentRequestor.getString(R.string.notification_channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = this.currentRequestor.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Date start = getCompundStartTime(Type.FROM_TIME);
        Date current = new GregorianCalendar().getTime();
        long duration = Math.abs(current.getTime() - start.getTime());

        //Log.d(TAG, "updateNotification: Started @ " + start + " current @ " + current +
        //        " is difference of " + duration + " ms. ");

        int minutes = (int) ((duration/1000)/60 % 60);
        int hours = (int) ((duration/1000)/60/60 % 24);
        int days = (int) ((duration/1000)/60/60/24);

        // Log.d("TimeHandler", "updateNotification: Time is: " + duration + " -> " + days + ":" + hours + ":" + minutes);
        String content = this.currentRequestor.getString(R.string.notification_message) + " " +
                (days > 0 && days < 356 ? Integer.toString(days) + ":" : "") +
                (hours < 10 ? "0" : "") + Integer.toString(hours) + ":" +
                (minutes < 10 ? "0" : "") + Integer.toString(minutes);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm zZ");
        String thirdLine =
                this.currentRequestor.getString(R.string.notification_message_additional_line) + " " +
                        format.format(start);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this.currentRequestor, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_menu_times)
                .setContentTitle(this.currentRequestor.getString(R.string.notification_title))
                .setContentText(content)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine(content)
                        .addLine(thirdLine))
                .setVibrate(null)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this.currentRequestor);

        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    private Date getCompundStartTime(Type type) {
        switch(type){
            case FROM_DATE:
            case FROM_TIME:
            case FROM_TIMEZONE: {
                Calendar c = new GregorianCalendar();
                Date monthAndDay = ((TimeModel) this.value.get(Type.FROM_DATE)).getValue();
                Date hourAndMinute = ((TimeModel) this.value.get(Type.FROM_TIME)).getValue();
                TimeZone tz = ((TimeZoneModel) this.value.get(Type.FROM_TIMEZONE)).getValue();
                // Log.d(TAG, "getCompundStartTime: got for monthandday: " + monthAndDay);
                // Log.d(TAG, "getCompundStartTime: got for hourandminute: " + hourAndMinute);
                c.setTime(monthAndDay);
                c.setTimeZone(tz);
                return c.getTime();
            }
            case TO_DATE:
            case TO_TIME:
            case TO_TIMEZONE: {
                Calendar c = new GregorianCalendar();
                Date monthAndDay = ((TimeModel) this.value.get(Type.TO_DATE)).getValue();
                Date hourAndMinute = ((TimeModel) this.value.get(Type.TO_TIME)).getValue();
                TimeZone tz = ((TimeZoneModel) this.value.get(Type.TO_TIMEZONE)).getValue();
                c.set(monthAndDay.getYear(), monthAndDay.getMonth(), monthAndDay.getDate(), hourAndMinute.getHours(), monthAndDay.getMinutes());
                c.setTimeZone(tz);
                return c.getTime();
            }
        }
        return new GregorianCalendar().getTime();
    }
}
