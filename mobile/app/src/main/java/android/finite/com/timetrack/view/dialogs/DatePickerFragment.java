package android.finite.com.timetrack.view.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.finite.com.timetrack.controller.Controllers;
import android.finite.com.timetrack.controller.TimeHandler;
import android.finite.com.timetrack.model.values.TimeModel;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment implements PickerFragment{
    private EditText input = null;
    private TimeHandler.Type flavour;
    private TimeModel model;

    @Override
    public void setSource(EditText input) {
        this.input = input;
    }

    @Override
    public void setFlavour(TimeHandler.Type flav) {
        this.flavour = flav;
    }

    @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            TimeHandler controller = Controllers.get().getController(TimeHandler.class);
            this.model = controller.getTime(this.flavour);

            Calendar c = new GregorianCalendar();
            c.setTime(this.model.getValue());

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
        }

        private DatePickerDialog.OnDateSetListener dateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String dateString = view.getDayOfMonth() + "." + (view.getMonth()+1) + "." + view.getYear();
                        input.setText(dateString);
                    }
                };

}
