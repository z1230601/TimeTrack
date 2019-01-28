package android.finite.com.timetrack.view.spinner;

import android.app.Activity;
import android.finite.com.timetrack.db.data.Assignment;
import android.finite.com.timetrack.R;
import android.finite.com.timetrack.data.DataManager;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import java.util.List;

public class AssigmentSpinnerAdapter extends ArrayAdapter<Assignment> implements SpinnerAdapter {
    private List<Assignment> assignments;

    public AssigmentSpinnerAdapter(Activity parent) {
        super(parent.getBaseContext(), R.layout.spinnerlayout, R.id.spinnerItem);
        init();
    }

    public void init() {
        clear();

        this.assignments = DataManager.get().getSelectedAssigments();
        for(Assignment assign : this.assignments) {
            add(assign);
        }

        notifyDataSetChanged();
    }

}
