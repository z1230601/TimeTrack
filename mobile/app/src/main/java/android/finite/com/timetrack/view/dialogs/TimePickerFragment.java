package android.finite.com.timetrack.view.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.finite.com.timetrack.controller.Controllers;
import android.finite.com.timetrack.controller.TimeHandler;
import android.finite.com.timetrack.model.values.TimeModel;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimePickerFragment extends DialogFragment implements PickerFragment {
    private EditText input = null;
    private TimeHandler.Type flavour;

    private TimePickerDialog.OnTimeSetListener timeSetListener =  new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hours, int minutes) {
            String dateString = (hours < 10 ? "0" : "") + hours + ":"  + (minutes < 10 ? "0" : "") + minutes;
            input.setText(dateString);
        }
    };
    private TimeModel model;


    public void setListener(TimePickerDialog.OnTimeSetListener listener) {
        this.timeSetListener = listener;
    }

    @Override
    public void setFlavour(TimeHandler.Type flav) {
        this.flavour = flav;
    }

    @Override
    public void setSource(EditText input) {
        this.input = input;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TimeHandler controller = Controllers.get().getController(TimeHandler.class);
        this.model = controller.getTime(this.flavour);

        Calendar c = new GregorianCalendar();
        c.setTime(this.model.getValue());
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), timeSetListener, hour, minutes, true);
    }


}
