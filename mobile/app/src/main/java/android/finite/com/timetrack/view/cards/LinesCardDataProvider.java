package android.finite.com.timetrack.view.cards;


import android.content.Context;
import android.finite.com.utility.TextLayout;
import android.finite.com.utility.Tuple;

import java.util.List;

public abstract class LinesCardDataProvider implements CardDataProvider {
    public CardLayout createLayout(Context context) {
        return new LinesCardLayout(context, this);
    }

    public abstract Tuple<TextLayout, String> getHeadLine();
    public abstract Tuple<TextLayout, List<String>> getContentLines();
    public abstract Tuple<TextLayout, String> getFootNote();
}

