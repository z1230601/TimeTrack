package android.finite.com.timetrack.view.cards;

import android.finite.com.data.Assignment;
import android.support.v7.widget.CardView;

public interface CardSelector {
    void setSelectedCard(CardView cardView, Object associacation);
}
