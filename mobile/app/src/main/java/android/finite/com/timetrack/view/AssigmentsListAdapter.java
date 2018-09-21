package android.finite.com.timetrack.view;

import android.finite.com.data.Assignment;
import android.finite.com.timetrack.R;
import android.finite.com.timetrack.data.DataManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AssigmentsListAdapter extends  RecyclerView.Adapter<AssigmentsListAdapter.DataViewHolder> {

    private final ArrayList<Assignment> data;

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public DataViewHolder(CardView v) {
            super(v);
            cardView = v;

        }
    }

    public AssigmentsListAdapter() {
        this.data = DataManager.get().getAssignments();

    }

    // Create new views (invoked by the layout manager)
    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assignment_list_item, parent, false);
        return new AssigmentsListAdapter.DataViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        // holder.mTextView.setText(this.data.get(position).getReadableString());

    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
