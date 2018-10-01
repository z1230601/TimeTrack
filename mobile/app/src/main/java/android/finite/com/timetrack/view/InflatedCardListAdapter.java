package android.finite.com.timetrack.view;

import android.finite.com.data.Assignment;
import android.finite.com.timetrack.R;
import android.finite.com.timetrack.data.DataManager;
import android.finite.com.timetrack.view.cards.CardSelector;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class InflatedCardListAdapter extends  RecyclerView.Adapter<InflatedCardListAdapter.DataViewHolder> {

    private List<Assignment> data;
    private final CardSelector parent;

    public static class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cardView;
        private CardSelector parent;
        private Assignment assignment;

        public DataViewHolder(CardView card)  {
            super(card);
            cardView = card;
            this.cardView.setOnClickListener(this);

        }

        public void onClick(View v) {
            cardView.setCardBackgroundColor(Color.WHITE);
            if(parent != null) { parent.setSelectedCard(cardView, assignment);}
        }

        public void fillData(Assignment assignment, CardSelector parent) {
            this.parent = parent;
            this.assignment = assignment;
            DateFormat dayFormat = new SimpleDateFormat("dd.MM");
            DateFormat yearFormat = new SimpleDateFormat("yyyy");
            TextView from = cardView.findViewById(R.id.fromDate);
            TextView to = cardView.findViewById(R.id.toDate);
            TextView year = cardView.findViewById(R.id.yearText);
            from.setText(dayFormat.format(assignment.getFromDate()));
            to.setText(dayFormat.format(assignment.getToDate()));
            if(assignment.getToDate().getYear() == assignment.getToDate().getYear()){
                year.setText(yearFormat.format(assignment.getToDate()));
            }else {
                year.setText(yearFormat.format(assignment.getFromDate())
                        + "/" + yearFormat.format(assignment.getToDate()));
            }

            TextView name = this.cardView.findViewById(R.id.assignmentName);
            name.setText(assignment.getShortName());
            TextView description = this.cardView.findViewById(R.id.shortDescription);
            description.setText(assignment.getShortDescription());
        }
    }

    public InflatedCardListAdapter(CardSelector cardSelector) {
        this.parent = cardSelector;

    }

    public void setProject(int projectId) {
        this.data = DataManager.get().getAssignmentsForProject(projectId);
        notifyDataSetChanged();
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InflatedCardListAdapter.DataViewHolder((CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assignment_list_item, parent, false));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.fillData((Assignment) this.data.get(position), this.parent);
    }

    @Override
    public int getItemCount() {
        if(this.data == null) {
            return 0;
        }
        return this.data.size();
    }
}
