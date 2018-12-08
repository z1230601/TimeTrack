package android.finite.com.timetrack;

import android.app.DialogFragment;
import android.finite.com.timetrack.controller.TimeHandler;
import android.finite.com.timetrack.view.dialogs.DatePickerFragment;
import android.finite.com.timetrack.view.dialogs.TimePickerFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimeZone;

public class TimeAddActivity extends AppCompatActivity {

    private EditText fromDate;
    private EditText toDate;
    private EditText fromTime;
    private EditText toTime;
    private TimeHandler timeHandler;
    private AutoCompleteTextView fromTmz;
    private AutoCompleteTextView toTmz;
    private ArrayAdapter<String> fromTimeZoneAdapter;
    private ArrayAdapter<String> toTimeZoneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.timeHandler = new TimeHandler();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.saveNewTimeFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeHandler.saveTimeEntry(fromDate.getText().toString(), fromTime.getText().toString(),
                        toDate.getText().toString(), toTime.getText().toString());
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initDates();
        initTimes();
        initTimesZones();


    }

    private void initTimesZones() {
        this.fromTmz = (AutoCompleteTextView) findViewById(R.id.fromTmz);
        this.toTmz = (AutoCompleteTextView) findViewById(R.id.toTmz);

        ArrayList<String> timezones = new ArrayList<String>(Arrays.asList(TimeZone.getAvailableIDs()));

        this.fromTimeZoneAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, timezones);
        this.toTimeZoneAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, timezones);

        this.fromTmz.setAdapter(this.fromTimeZoneAdapter);
        this.toTmz.setAdapter(this.toTimeZoneAdapter);
        this.fromTmz.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    if(!isTimeZoneValid(fromTmz)) {
                        fromTmz.setError(getResources().getString(R.string.TMZ_UNKNOWN));
                    }
                }
            }
        });
    }

    private boolean isTimeZoneValid(AutoCompleteTextView fromTmz) {
        ArrayList<String> data = new ArrayList<String>(Arrays.asList(TimeZone.getAvailableIDs()));
        String setTmz = fromTmz.getText().toString();
        return data.contains(setTmz);
    }

    private void initTimes() {
        this.fromTime = (EditText) findViewById(R.id.fromTime);
        this.toTime = (EditText) findViewById(R.id.toTime);

        fromTime.setClickable(true);
        fromTime.setKeyListener(null);
        fromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { if(view instanceof EditText) {showTimePicker(fromTime, toTime);} }
        });
        fromTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                if(view instanceof EditText && b) {
                    showTimePicker(fromTime, toTime);
                }
            }
        });

        toTime.setClickable(true);
        toTime.setKeyListener(null);
        toTime.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view instanceof EditText) {
                    showTimePicker(toTime, null);
                }
            }
        });
        toTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                if(view instanceof EditText && b) {
                    showTimePicker(toTime, null);
                }
            }
        });
    }

    private void initDates() {
        this.fromDate = (EditText) findViewById(R.id.fromDate);
        this.toDate = (EditText) findViewById(R.id.toDate);
        fromDate.setClickable(true);
        fromDate.setKeyListener(null);
        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {if(view instanceof EditText) {showDatePicker(fromDate, toDate);}}
        });
        fromDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {if(view instanceof EditText && b) {showDatePicker(fromDate, toDate);}}
        });

        toDate.setClickable(true);
        toDate.setKeyListener(null);
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view instanceof EditText) {
                    showDatePicker(toDate, null);
                }
            }
        });
        toDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                if(view instanceof EditText && b) {
                    showDatePicker(toDate, null);
                }
            }
        });
    }

    public void showDatePicker(EditText fromDate, EditText toDate) {
        DialogFragment newFragment = new DatePickerFragment();
        ((DatePickerFragment) newFragment).setSource(fromDate, toDate);
        newFragment.show(getFragmentManager(), "Date Picker");
    }

    private void showTimePicker(EditText fromTime, EditText toTime) {
        DialogFragment newFragment = new TimePickerFragment();
        ((TimePickerFragment) newFragment).setSource(fromTime, toTime);
        newFragment.show(getFragmentManager(), "Time Picker");
    }


}
