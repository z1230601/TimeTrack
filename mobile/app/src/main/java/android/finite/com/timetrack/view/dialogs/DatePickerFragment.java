package android.finite.com.timetrack.view.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    private EditText input = null;
    private EditText associatedInput = null;

    public void setSource(EditText input, EditText associatedInput) {
        this.input = input;
        this.associatedInput = associatedInput;
    }

    @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
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
                        if(associatedInput != null) {
                            associatedInput.setText(dateString);
                        }
                    }
                };

}
