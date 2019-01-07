package android.finite.com.timetrack.view.dialogs;

import android.finite.com.timetrack.controller.TimeHandler;
import android.widget.EditText;

public interface PickerFragment {
    void setSource(EditText editText);
    void setFlavour(TimeHandler.Type type);
}
