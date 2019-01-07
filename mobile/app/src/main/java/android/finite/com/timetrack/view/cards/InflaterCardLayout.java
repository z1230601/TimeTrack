package android.finite.com.timetrack.view.cards;

import android.view.View;
import android.view.ViewGroup;

public class InflaterCardLayout extends CardLayout {
    private final ViewGroup parent;

    public InflaterCardLayout(ViewGroup parent, int times_list_item) {
        this.parent = parent;

    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public void fill() {

    }

    @Override
    public CardDataProvider getDataProvider() {
        return null;
    }

    @Override
    public void setProvider(CardDataProvider provider) {

    }
}
