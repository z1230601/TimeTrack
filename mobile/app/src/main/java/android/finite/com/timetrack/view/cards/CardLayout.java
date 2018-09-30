package android.finite.com.timetrack.view.cards;

import android.view.View;

abstract class CardLayout {
    public abstract View getRootView();
    public abstract void fill();

    public abstract CardDataProvider getDataProvider();

    public abstract void setProvider(CardDataProvider provider);
}
