package android.finite.com.timetrack.model;

import android.finite.com.timetrack.view.wrapper.ViewWrapper;

import java.util.ArrayList;
import java.util.Date;

public abstract class Model<ValueT> {
    protected ValueT value = null;

    protected ArrayList<ViewWrapper> observers = new ArrayList<>();


    public void registerModel(ViewWrapper view) {
        this.observers.add(view);
        view.notifyModelChanged(this);
    }

    public abstract String getReadableString();

    public ValueT getValue() {
        return this.value;
    }

    public void setValue(ValueT value) {
        this.value = value;
    }
}
