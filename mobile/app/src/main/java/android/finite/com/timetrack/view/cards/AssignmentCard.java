package android.finite.com.timetrack.view.cards;

import android.content.Context;
import android.finite.com.data.Assignment;
import android.finite.com.data.Project;
import android.finite.com.timetrack.ProjectsView;
import android.finite.com.utility.Convertors;
import android.finite.com.utility.TextLayout;
import android.finite.com.utility.Tuple;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.TextView;

import java.util.List;

import static android.graphics.Typeface.BOLD;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class AssignmentCard extends BaseCardView {
    private  Assignment assignment = null;

    public AssignmentCard(@NonNull Context context, Assignment assignment, CardSelector parent) {
        super(context, parent);
        this.assignment = assignment;
    }

    @Override
    public void create() {
        if(this.assignment == null ){
            return;
        }
        this.linesLayout.removeAllViews();
        this.lines.clear();

        this.lines.add(getFreshTextView(this.assignment.getHeadLine()));
        Tuple<TextLayout, List<String>> contentLines = this.assignment.getContentLines();
        for(String line : contentLines.second) {
            this.lines.add(getFreshTextView(new Tuple<TextLayout, String>(contentLines.first, line)));

        }
        this.lines.add(getFreshTextView(this.assignment.getFootNote()));

        for(TextView line : lines) {
            this.linesLayout.addView(line);
        }
    }

    public Assignment getAssociatedAssignment() {
        return this.assignment;
    }
}
