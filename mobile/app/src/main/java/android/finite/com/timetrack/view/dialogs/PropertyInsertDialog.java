package android.finite.com.timetrack.view.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.finite.com.timetrack.R;
import android.finite.com.utility.Tuple;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PropertyInsertDialog extends DialogFragment {
    private View dialogContent;
    private PropertyDialogListener listener = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        this.dialogContent = getActivity().getLayoutInflater().inflate(R.layout.dialog_property_content, null);

        builder
                .setMessage(R.string.dialog_property_message)
                .setView(this.dialogContent)
                .setPositiveButton(R.string.dialog_property_confirm_button,
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText keyText = (EditText) dialogContent.findViewById(R.id.keyText);
                        EditText valueText = (EditText) dialogContent.findViewById(R.id.valueText);

                        listener.setProperty(new Tuple<String, String>(keyText.getText().toString(), valueText.getText().toString()));
                    }
                })
                .setNegativeButton(R.string.dialog_property_cancel_button,
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            this.listener = (PropertyDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }


    public interface PropertyDialogListener {
        void setProperty(Tuple<String, String> data);
    }
}
