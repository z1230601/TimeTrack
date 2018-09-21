package android.finite.com.timetrack.view;

import android.app.Activity;
import android.finite.com.data.Project;
import android.finite.com.timetrack.R;
import android.finite.com.timetrack.data.DataManager;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import java.util.List;

public class ProjectSpinnerAdapter extends ArrayAdapter<Project> implements SpinnerAdapter {
    private final List<Project> projects;

    public ProjectSpinnerAdapter(Activity parent){
        super(parent.getBaseContext(), R.layout.spinnerlayout, R.id.spinnerItem);
        this.projects = DataManager.get().getProjects();
        for(Project proj : this.projects) {
            add(proj);
        }
    }
}
