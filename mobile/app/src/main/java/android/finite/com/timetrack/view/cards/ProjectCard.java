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
        this.linesLayout.removeAllViews();
        this.lines.clear();

        this.lines.add(getFreshTextView(this.project.getHeadLine()));
        Tuple<TextLayout, List<String>> contentLines = this.project.getContentLines();
        for(String line : contentLines.second) {
            this.lines.add(getFreshTextView(new Tuple<TextLayout, String>(contentLines.first, line)));

        }
        this.lines.add(getFreshTextView(this.project.getFootNote()));

        for(TextView line : lines) {
            this.linesLayout.addView(line);
        }
    }


    public Project getAssociatedProject() {
        return this.project;
    }
}
