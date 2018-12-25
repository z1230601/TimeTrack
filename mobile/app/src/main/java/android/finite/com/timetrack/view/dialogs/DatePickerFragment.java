package android.finite.com.timetrack.view.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.finite.com.timetrack.controller.TimeHandler;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {
    enum Flavour{
        FROM,
        TO
    }
    private EditText input = null;
    private Flavour flavour;

    public void setSource(EditText input) {
        this.input = input;
    }


    public void setFlavour(Flavour flav) {
        this.flavour = flav;
    }

    @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            String dateString = this.input.getText().toString();

            Calendar c = null;
            try {
                Date date = TimeHandler.getDateFormat().parse(dateString);
                c = new GregorianCalendar();
                c.setTime(date);
            } catch (ParseException e) {
                c = Calendar.getInstance();
            }

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
