package android.finite.com.timetrack.view.wrapper;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.finite.com.timetrack.controller.TimeHandler;
import android.finite.com.timetrack.model.Model;
import android.finite.com.timetrack.model.values.TimeModel;
import android.finite.com.timetrack.view.dialogs.PickerFragment;
import android.view.View;
import android.widget.EditText;

public class TimeEditTextWrapper extends EditTextWrapper {
    private FragmentManager fragmentManager;
    private PickerFragment picker;
    private TimeHandler.Type type;


    public TimeEditTextWrapper(EditText editText, TimeHandler.Type type, TimeHandler timeHandler,
                               PickerFragment pickerFragment, FragmentManager fragmentManager) {
        super(editText);
        this.type = type;

        timeHandler.getTime(this.type).registerModel(this);

        initView();
        this.picker = pickerFragment;
        this.fragmentManager = fragmentManager;
    }

    private void initView() {
        this.editText.setClickable(true);
        this.editText.setKeyListener(null);
        this.editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view instanceof EditText) {
                    showPicker();
                }
            }
        });
        this.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                if(view instanceof EditText && b) {
                    showPicker();
                }
            }
        });
    }

    private void showPicker() {
        this.picker.setSource(this.editText);
        this.picker.setFlavour(this.type);
        if(this.picker instanceof DialogFragment){
            ((DialogFragment) this.picker).show(this.fragmentManager, "TimeModel & Date Picker");
        }
    }

    @Override
    public void notifyModelChanged(Model model) {
        if(model instanceof TimeModel) {
            this.editText.setText(((TimeModel) model).getReadableString(this.type));
        }
    }
}
