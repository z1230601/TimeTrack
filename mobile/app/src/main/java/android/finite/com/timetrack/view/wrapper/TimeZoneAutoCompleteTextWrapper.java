package android.finite.com.timetrack.view.wrapper;

import android.content.Context;
import android.content.res.Resources;
import android.finite.com.timetrack.R;
import android.finite.com.timetrack.controller.TimeHandler;
import android.finite.com.timetrack.model.Model;
import android.finite.com.timetrack.model.values.TimeZoneModel;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class TimeZoneAutoCompleteTextWrapper extends ViewWrapper {
    private TimeZoneModel timeZoneModel;
    private ArrayAdapter<String> timeZoneAdapter;
    private AutoCompleteTextView completeTextView;

    public TimeZoneAutoCompleteTextWrapper(AutoCompleteTextView view, TimeHandler.Type type,
                                           TimeHandler timeHandler, final Resources resources,
                                           Context context) {
        this.completeTextView = view;
        this.timeZoneModel = timeHandler.getTimeZone(type);

        this.timeZoneAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line,
                this.timeZoneModel.getAvailableTimeZones());

        this.completeTextView.setAdapter(this.timeZoneAdapter);

        this.completeTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    if(!timeZoneModel.isTimeZoneValid(completeTextView.getText().toString())) {
                        completeTextView.setError(resources.getString(R.string.TMZ_UNKNOWN));
                    }
                }
            }
        });

        this.timeZoneModel.registerModel(this);
    }

    @Override
    public void notifyModelChanged(Model model) {
        this.completeTextView.setText(this.timeZoneModel.getReadableString());
    }
}
