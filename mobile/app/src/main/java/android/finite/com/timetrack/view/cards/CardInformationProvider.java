package android.finite.com.timetrack.view.cards;

import android.finite.com.utility.TextLayout;
import android.finite.com.utility.Tuple;

import java.util.List;

public interface CardInformationProvider {
    Tuple<TextLayout, String> getHeadLine();
    Tuple<TextLayout, List<String>> getContentLines();
    Tuple<TextLayout, String> getFootNote();
}
