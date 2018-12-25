package android.finite.com.timetrack.view.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.finite.com.timetrack.controller.TimeHandler;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimePickerFragment extends DialogFragment {
    enum Flavour{
        FROM,
        TO
    }
    private EditText input = null;
    private Flavour flavour;

    private TimePickerDialog.OnTimeSetListener timeSetListener =  new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hours, int minutes) {
            String dateString = (hours < 10 ? "0" : "") + hours + ":"  + (minutes < 10 ? "0" : "") + minutes;
            input.setText(dateString);
        }
    };


    public void setListener(TimePickerDialog.OnTimeSetListener listener) {
        this.timeSetListener = listener;
    }

    public void setFlavour(Flavour flav) {
        this.flavour = flav;
    }

    public void setSource(EditText input) {
        this.input = input;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = null;
        try {
            Date date = TimeHandler.getTimeFormat().parse(this.input.getText().toString());
            c = new GregorianCalendar();
            c.setTime(date);
        } catch (ParseException e) {
            c = Calendar.getInstance();
        }
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), timeSetListener, hour, minutes, true);
    }


}
