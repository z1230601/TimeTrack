package android.finite.com.timetrack.view.cards;

import android.content.Context;
import android.finite.com.data.Project;
import android.finite.com.timetrack.ProjectsView;
import android.finite.com.utility.Convertors;
import android.finite.com.utility.TextLayout;
import android.finite.com.utility.Tuple;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import static android.graphics.Typeface.BOLD;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class ProjectCard extends BaseCardView {
    private Project project = null;

    public ProjectCard(@NonNull Context context, Project project, ProjectsView parent) {
        super(context, parent);
        this.project = project;
    }

    @Override
    public void create() {
        if(this.project == null ){
            return;
        }
        this.lines.add(getFreshTextView(this.project.getHeadLine()));
        Tuple<TextLayout, List<String>> contentLines = this.project.getContentLines();
        for(String line : contentLines.second) {
            this.lines.add(getFreshTextView(new Tuple<TextLayout, String>(contentLines.first, line)));
        }
        this.lines.add(getFreshTextView(this.project.getFootNote()));
    }

    public TextView getFreshTextView(Tuple<TextLayout, String> data) {
        TextView freshTextView = new TextView(getContext());
        MarginLayoutParams params = (MarginLayoutParams) freshTextView.getLayoutParams();
        if(params == null ) {
            params = new MarginLayoutParams(MATCH_PARENT, WRAP_CONTENT);
        }
        params.width = MATCH_PARENT;
        params.height = WRAP_CONTENT;
        freshTextView.setLayoutParams(params);
        if(data.first == TextLayout.LARGE) {
            freshTextView.setTextSize(Convertors.convertSpToPx(freshTextView.getContext(), 9));
            freshTextView.setTypeface(null, BOLD);
        }
        if(data.first == TextLayout.SMALL) {
            freshTextView.setTextSize(Convertors.convertSpToPx(freshTextView.getContext(), 4));
        }

        freshTextView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        freshTextView.setText(data.second);
        return freshTextView;
    }

    public Project getAssociatedProject() {
        return this.project;
    }
}
