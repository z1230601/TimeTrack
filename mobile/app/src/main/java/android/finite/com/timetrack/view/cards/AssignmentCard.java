package android.finite.com.timetrack.view.cards;

import android.content.Context;
import android.finite.com.data.Assignment;
import android.finite.com.data.Project;
import android.finite.com.timetrack.ProjectsView;
import android.finite.com.utility.Convertors;
import android.finite.com.utility.TextLayout;
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
//        List<String> linesToDisplay = this.assignment.getShortSummary();
//        for (int i =0; i < linesToDisplay.size(); i++) {
//            String line = linesToDisplay.get(i);
//            TextLayout layout = TextLayout.SMALL;
//            if(i==0) {
//                layout = TextLayout.LARGE;
//            }
//            if(i==linesToDisplay.size()-1) {
//                layout = TextLayout.MEDIUM;
//            }
//            TextView lineView = getFreshTextView(layout);
//            lineView.setText(line);
//            this.lines.add(lineView);
//            linesLayout.addView(lineView);
//        }
    }

    public TextView getFreshTextView(TextLayout layout) {
        TextView freshTextView = new TextView(getContext());
        MarginLayoutParams params = (MarginLayoutParams) freshTextView.getLayoutParams();
        if(params == null ) {
            params = new MarginLayoutParams(MATCH_PARENT, WRAP_CONTENT);
        }
        params.width = MATCH_PARENT;
        params.height = WRAP_CONTENT;
        freshTextView.setLayoutParams(params);
        if(layout == TextLayout.LARGE) {
            freshTextView.setTextSize(Convertors.convertSpToPx(freshTextView.getContext(), 9));
            freshTextView.setTypeface(null, BOLD);
        }
        if(layout == TextLayout.SMALL) {
            freshTextView.setTextSize(Convertors.convertSpToPx(freshTextView.getContext(), 4));
        }

        freshTextView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        return freshTextView;
    }

    public Assignment getAssociatedAssignment() {
        return this.assignment;
    }
}
