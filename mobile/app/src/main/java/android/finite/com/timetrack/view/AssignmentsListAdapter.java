package android.finite.com.timetrack.view;

import android.finite.com.data.Assignment;
import android.finite.com.timetrack.data.DataManager;
import android.finite.com.timetrack.view.cards.AssignmentCard;
import android.finite.com.timetrack.view.cards.CardSelector;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class AssignmentsListAdapter extends  RecyclerView.Adapter<AssignmentsListAdapter.DataViewHolder> {

    private List<Assignment> data;
    private final CardSelector parent;

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        public AssignmentCard cardView;
        public DataViewHolder(AssignmentCard card) {
            super(card);
            cardView = card;

        }

        public void fillData(Assignment assignment, CardSelector parent) {
            this.cardView.setParent(parent);
            this.cardView.setAssigment(assignment);
        }
    }

    public AssignmentsListAdapter(CardSelector cardSelector) {
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
        AssignmentCard card = new AssignmentCard(parent.getContext());
        return new AssignmentsListAdapter.DataViewHolder(card);
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
