package android.finite.com.timetrack.view.wrapper;

import android.widget.EditText;

public abstract class EditTextWrapper extends ViewWrapper {
    protected EditText editText;

    public EditTextWrapper(EditText edit) {
        this.editText = edit;
    }
}
