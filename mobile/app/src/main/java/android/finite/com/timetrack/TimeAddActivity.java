package android.finite.com.timetrack;

import android.content.Context;
import android.finite.com.timetrack.controller.Controllers;
import android.finite.com.timetrack.controller.TimeHandler;
import android.finite.com.timetrack.view.dialogs.DatePickerFragment;
import android.finite.com.timetrack.view.dialogs.TimePickerFragment;
import android.finite.com.timetrack.view.wrapper.TimeEditTextWrapper;
import android.finite.com.timetrack.view.wrapper.TimeZoneAutoCompleteTextWrapper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

public class TimeAddActivity extends AppCompatActivity {

    private TimeEditTextWrapper fromDate;
    private TimeEditTextWrapper fromTime;
    private TimeZoneAutoCompleteTextWrapper fromTimeZone;

    private TimeEditTextWrapper toDate;
    private TimeEditTextWrapper toTime;
    private TimeZoneAutoCompleteTextWrapper toTimeZone;

    private TimeHandler timeHandler;
    private Spinner type;
    private ArrayAdapter<String> typeAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.timeHandler = Controllers.get().getController(TimeHandler.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.saveNewTimeFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                timeHandler.saveTimeEntry(fromDate.getText().toString(), fromTime.getText().toString(), fromTmz.getText().toString(),
//                        toDate.getText().toString(), toTime.getText().toString(), toTmz.getText().toString(),
//                        (String) type.getSelectedItem());
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initType();
        this.fromDate = new TimeEditTextWrapper((EditText) findViewById(R.id.fromDate),
                TimeHandler.Type.FROM_DATE, this.timeHandler,
                new DatePickerFragment(), getFragmentManager());

        this.toDate = new TimeEditTextWrapper((EditText) findViewById(R.id.toDate),
                TimeHandler.Type.TO_DATE, this.timeHandler,
                new DatePickerFragment(), getFragmentManager());
        this.fromTime = new TimeEditTextWrapper((EditText) findViewById(R.id.fromTime),
                TimeHandler.Type.FROM_TIME, this.timeHandler,
                new TimePickerFragment(), getFragmentManager());
        this.toTime = new TimeEditTextWrapper((EditText) findViewById(R.id.toTime),
                TimeHandler.Type.TO_TIME, this.timeHandler, new TimePickerFragment(), getFragmentManager());

        this.fromTimeZone = new TimeZoneAutoCompleteTextWrapper((AutoCompleteTextView) findViewById(R.id.fromTmz),
                TimeHandler.Type.FROM_TIMEZONE, this.timeHandler, getResources(), (Context) this);
        this.toTimeZone = new TimeZoneAutoCompleteTextWrapper((AutoCompleteTextView) findViewById(R.id.toTmz),
                TimeHandler.Type.TO_TIMEZONE, this.timeHandler, getResources(), (Context) this);
    }

    private void initType() {
        this.type = (Spinner) findViewById(R.id.typeSpinner);
        this.typeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, this.timeHandler.getTypes());
        this.type.setAdapter(this.typeAdapter);
    }
}
