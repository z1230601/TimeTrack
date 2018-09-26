package android.finite.com.timetrack.view.cards;

import android.content.Context;
import android.finite.com.utility.Convertors;
import android.finite.com.utility.TextLayout;
import android.finite.com.utility.Tuple;
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

public abstract class BaseCardView extends CardView implements View.OnClickListener {
    protected final LinearLayout linesLayout;
    protected CardSelector parent = null;
    protected List<TextView> lines = new ArrayList<TextView>();
    protected boolean selected = false;


    public abstract void create();
    public BaseCardView(@NonNull Context context) {
        super(context);

        initStyle();

        this.linesLayout = new LinearLayout(context);
        this.linesLayout.setLayoutParams(new ViewGroup.MarginLayoutParams(MATCH_PARENT, MATCH_PARENT));
        this.linesLayout.setOrientation(LinearLayout.VERTICAL);
        addView(this.linesLayout);
        setOnClickListener(this);
    }

    public BaseCardView(@NonNull Context context, CardSelector parent) {
        super(context);
        this.parent = parent;

        initStyle();

        this.linesLayout = new LinearLayout(context);
        this.linesLayout.setLayoutParams(new ViewGroup.MarginLayoutParams(MATCH_PARENT, MATCH_PARENT));
        this.linesLayout.setOrientation(LinearLayout.VERTICAL);
        addView(this.linesLayout);
        setOnClickListener(this);
    }

    public void initStyle() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getLayoutParams();
        if(params == null) {
            params = new ViewGroup.MarginLayoutParams(MATCH_PARENT, MATCH_PARENT);
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

    public TextView getFreshTextView(TextLayout layout) {
        TextView freshTextView = new TextView(getContext());
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) freshTextView.getLayoutParams();
        if(params == null ) {
            params = new ViewGroup.MarginLayoutParams(MATCH_PARENT, WRAP_CONTENT);
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

    public TextView getFreshTextView(Tuple<TextLayout, String> data) {
        if(data == null) {
            return null;
        }

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

    @Override
    public void onClick(View v) {
        resetSelection();
        this.parent.setSelectedCard(this);
    }

    public void resetSelection() {
        setCardBackgroundColor(Color.WHITE);
    }

    public void setParent(CardSelector parent) {
        this.parent = parent;
    }
}