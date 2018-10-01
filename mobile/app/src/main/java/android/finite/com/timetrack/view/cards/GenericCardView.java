package android.finite.com.timetrack.view.cards;

import android.content.Context;
import android.finite.com.data.Assignment;
import android.finite.com.data.Project;
import android.finite.com.utility.Convertors;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


public class GenericCardView extends CardView implements View.OnClickListener {
    private CardLayout layoutManger;
    protected CardSelector parent = null;
    protected boolean selected = false;

    public GenericCardView(Context context, CardLayout layout) {
        super(context);
        this.layoutManger = layout;

        initStyle();

        addView(this.layoutManger.getRootView());
        setOnClickListener(this);
    }

    public void create() {
        this.layoutManger.fill();
    }

    public GenericCardView(@NonNull Context context, CardSelector parent, CardLayout layout) {
        super(context);
        this.layoutManger = layout;
        this.parent = parent;

        initStyle();

        addView(this.layoutManger.getRootView());
        setOnClickListener(this);
    }

    public GenericCardView(@NonNull Context context, CardSelector parent, CardDataProvider layoutData) {
        super(context);
        this.layoutManger = layoutData.createLayout(context);
        this.parent = parent;

        initStyle();

        addView(this.layoutManger.getRootView());
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

    @Override
    public void onClick(View v) {
        resetSelection();
        if(this.parent != null) {
            this.parent.setSelectedCard(this, this.layoutManger.getDataProvider());
        }
    }

    public void resetSelection() {
        setCardBackgroundColor(Color.WHITE);
    }

    public void setParent(CardSelector parent) {
        this.parent = parent;
    }

    public Object getAssociatedDataProvder() {
        return this.layoutManger.getDataProvider();
    }

    public void setProvider(CardDataProvider provider) {
        this.layoutManger.setProvider(provider);
    }
}