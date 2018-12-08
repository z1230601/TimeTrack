package android.finite.com.timetrack.view.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {
    private EditText input = null;
    private EditText associatedInput = null;

    public void setSource(EditText input, EditText associatedInput) {
        this.input = input;
        this.associatedInput = associatedInput;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minutes = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), dateSetListener, hour, minutes, true);
    }

    private TimePickerDialog.OnTimeSetListener dateSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hours, int minutes) {
                    String dateString = hours + ":" + minutes;
                    input.setText(dateString);
                    if(associatedInput != null) {
                        associatedInput.setText(dateString);
                    }
                }
            };

}
