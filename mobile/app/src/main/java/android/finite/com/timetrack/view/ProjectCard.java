package android.finite.com.timetrack.view;

import android.content.Context;
import android.content.Intent;
import android.finite.com.data.Project;
import android.finite.com.timetrack.ProjectsView;
import android.finite.com.utility.Convertors;
import android.finite.com.utility.TextLayout;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Typeface.BOLD;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class ProjectCard extends CardView implements View.OnClickListener {
    private final LinearLayout linesLayout;
    private final ProjectsView parent;
    private Project project = null;
    private List<TextView> lines = new ArrayList<TextView>();
    private boolean selected = false;

    public ProjectCard(@NonNull Context context, Project project, ProjectsView parent) {
        super(context);
        this.project = project;
        this.parent = parent;

        initStyle();

        this.linesLayout = new LinearLayout(context);
        this.linesLayout.setLayoutParams(new MarginLayoutParams(MATCH_PARENT, MATCH_PARENT));
        this.linesLayout.setOrientation(LinearLayout.VERTICAL);
        addView(this.linesLayout);
        setOnClickListener(this);
    }

    public void initStyle() {
        ViewGroup.MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
        if(params == null) {
            params = new MarginLayoutParams(MATCH_PARENT, MATCH_PARENT);
        }
        params.width = MATCH_PARENT;
        params.height = WRAP_CONTENT;

        int marginValue = (int) Convertors.convertDpToPx(getContext(), 5.0f);

        params.setMargins(marginValue, marginValue, marginValue, marginValue);
        setLayoutParams(params);
        setRadius(marginValue);
        setCardElevation(marginValue);
        setPadding(marginValue, marginValue, marginValue, marginValue);
    }

    public void create() {

        if(this.project == null ){
            return;
        }
        List<String> linesToDisplay = this.project.getShortSummary();
        for (int i =0; i < linesToDisplay.size(); i++) {
            String line = linesToDisplay.get(i);
            TextLayout layout = TextLayout.SMALL;
            if(i==0) {
                layout = TextLayout.LARGE;
            }
            if(i==linesToDisplay.size()-1) {
                layout = TextLayout.MEDIUM;
            }
            TextView lineView = getFreshTextView(layout);
            lineView.setText(line);
            this.lines.add(lineView);
            linesLayout.addView(lineView);
        }
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

    @Override
    public void onClick(View v) {
        resetSelection();
        this.parent.setSelectedCard(this);
    }

    public void resetSelection() {
        setCardBackgroundColor(Color.WHITE);
    }

    public Project getAssociatedProject() {
        return this.project;
    }
}
