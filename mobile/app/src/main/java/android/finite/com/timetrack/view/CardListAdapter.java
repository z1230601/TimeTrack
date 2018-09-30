package android.finite.com.timetrack.view;

import android.finite.com.data.Assignment;
import android.finite.com.timetrack.data.DataManager;
import android.finite.com.timetrack.view.cards.CardSelector;
import android.finite.com.timetrack.view.cards.GenericCardView;
import android.finite.com.timetrack.view.cards.LinesCardLayout;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class CardListAdapter extends  RecyclerView.Adapter<CardListAdapter.DataViewHolder> {

    private List<Assignment> data;
    private final CardSelector parent;

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        public GenericCardView cardView;
        public DataViewHolder(GenericCardView card) {
            super(card);
            cardView = card;

        }

        public void fillData(Assignment assignment, CardSelector parent) {
            this.cardView.setParent(parent);
            this.cardView.setProvider(assignment);
        }
    }

    public CardListAdapter(CardSelector cardSelector) {
        this.parent = cardSelector;

    }

    public void setProject(int projectId) {
        this.data = DataManager.get().getAssignmentsForProject(projectId);
        System.out.println("Data changed to " + this.data.size());
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        GenericCardView card = new GenericCardView(parent.getContext(), new LinesCardLayout(parent.getContext()));
        return new CardListAdapter.DataViewHolder(card);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.fillData((Assignment) this.data.get(position), this.parent);
        holder.cardView.create();
    }

    @Override
    public int getItemCount() {
        if(this.data == null) {
            return 0;
        }
        return this.data.size();
    }
}
