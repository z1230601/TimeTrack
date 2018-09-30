package android.finite.com.timetrack.view.cards;

import android.content.Context;
import android.finite.com.utility.Convertors;
import android.finite.com.utility.TextLayout;
import android.finite.com.utility.Tuple;
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

public class LinesCardLayout extends CardLayout {
    private final LinearLayout linesLayout;
    private final Context context;
    private List<TextView> lines = new ArrayList<>();
    private LinesCardDataProvider linesCardDataProvider;

    public LinesCardLayout(Context context, LinesCardDataProvider linesCardDataProvider) {
        this.context = context;
        this.linesCardDataProvider = linesCardDataProvider;
        this.linesLayout = new LinearLayout(context);
        this.linesLayout.setLayoutParams(new ViewGroup.MarginLayoutParams(MATCH_PARENT, MATCH_PARENT));
        this.linesLayout.setOrientation(LinearLayout.VERTICAL);
    }

    public LinesCardLayout(Context context) {
        this.context = context;
        this.linesLayout = new LinearLayout(context);
        this.linesLayout.setLayoutParams(new ViewGroup.MarginLayoutParams(MATCH_PARENT, MATCH_PARENT));
        this.linesLayout.setOrientation(LinearLayout.VERTICAL);
    }

    public void fill() {
        if(this.linesCardDataProvider == null) {
            return;
        }

        this.linesLayout.removeAllViews();
        this.lines.clear();

        this.lines.add(getFreshTextView(this.linesCardDataProvider.getHeadLine()));
        Tuple<TextLayout, List<String>> contentLines = this.linesCardDataProvider.getContentLines();
        for(String line : contentLines.second) {
            this.lines.add(getFreshTextView(new Tuple<TextLayout, String>(contentLines.first, line)));

        }
        this.lines.add(getFreshTextView(this.linesCardDataProvider.getFootNote()));

        for(TextView line : lines) {
            this.linesLayout.addView(line);
        }
    }

    @Override
    public LinesCardDataProvider getDataProvider() {
        return this.linesCardDataProvider;
    }

    @Override
    public void setProvider(CardDataProvider provider) {
        this.linesCardDataProvider = (LinesCardDataProvider) provider;
    }

    @Override
    public View getRootView() {
        return this.linesLayout;
    }
    public TextView getFreshTextView(Tuple<TextLayout, String> data) {
        if(data == null) {
            return null;
        }

        TextView freshTextView = new TextView(this.context);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) freshTextView.getLayoutParams();
        if(params == null ) {
            params = new ViewGroup.MarginLayoutParams(MATCH_PARENT, WRAP_CONTENT);
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
}
