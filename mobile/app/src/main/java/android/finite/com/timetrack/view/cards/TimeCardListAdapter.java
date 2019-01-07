package android.finite.com.timetrack.view.cards;

import android.finite.com.timetrack.db.data.Assignment;
import android.finite.com.timetrack.db.data.TimeEntry;
import android.finite.com.timetrack.R;
import android.finite.com.timetrack.data.DataManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class TimeCardListAdapter extends  RecyclerView.Adapter<TimeCardListAdapter.DataViewHolder>{

    private List<TimeEntry> data;
    private final CardSelector parent;

    public static class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cardView;
        private CardSelector parent;
        private TimeEntry time;

        public DataViewHolder(CardView card)  {
            super(card);
            cardView = card;
            this.cardView.setOnClickListener(this);

        }

        public void onClick(View v) {
            cardView.setCardBackgroundColor(Color.WHITE);
            if(parent != null) { parent.setSelectedCard(cardView, time);}
        }

        public void fillData(TimeEntry assignment, CardSelector parent) {
            this.parent = parent;
            this.time = assignment;
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");
            DateFormat dayFormat = new SimpleDateFormat("dd.MM");
            TextView from = cardView.findViewById(R.id.fromDate);
            TextView to = cardView.findViewById(R.id.toDate);
            TextView year = cardView.findViewById(R.id.yearText);
            from.setText(timeFormat.format(assignment.getStart()));
            to.setText(timeFormat.format(assignment.getEnd()));
            if(assignment.getStart().getDay() == assignment.getEnd().getDay()){
                year.setText(dayFormat.format(assignment.getStart()));
            }else {
                year.setText(dayFormat.format(assignment.getStart())
                        + "/" + dayFormat.format(assignment.getEnd()));
            }
            TextView workDuration = this.cardView.findViewById(R.id.workingTimeData);
            workDuration.setText(this.time.getWorkDurationInSeconds()/3600 + " h");
            TextView breakDuration = this.cardView.findViewById(R.id.breakDuration);
            breakDuration.setText(this.time.getBreakDurationInSeconds()/60 + " min ");

            TextView doneTasks = this.cardView.findViewById(R.id.doneTasks);
            doneTasks.setText(this.time.getDoneTasks());

            TextView type = this.cardView.findViewById(R.id.type);
            type.setText(this.time.getType().name);

        }
    }

    public TimeCardListAdapter(CardSelector cardSelector) {
        this.parent = cardSelector;

    }

    public void setAssignment(Assignment assignment) {
        this.data = DataManager.get().getTimesForAssignment(assignment.getAssignmentId());
        notifyDataSetChanged();
    }

    @Override
    public TimeCardListAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //GenericCardView view = new GenericCardView(new InflaterCardLayout(parent, R.layout.times_list_item));
        return new TimeCardListAdapter.DataViewHolder((CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.times_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.fillData((TimeEntry) this.data.get(position), this.parent);
    }

    @Override
    public int getItemCount() {
        if(this.data == null) {
            return 0;
        }
        return this.data.size();
    }
}
