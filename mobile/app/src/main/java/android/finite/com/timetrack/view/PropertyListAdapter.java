package android.finite.com.timetrack.view;

import android.app.Activity;
import android.finite.com.timetrack.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyListAdapter.DataViewHolder> {
    private Activity parent;
    private List<Stringifyer> data = new ArrayList<>();
    private DataViewHolder lastSelected;


    public static class DataViewHolder extends RecyclerView.ViewHolder {
        private PropertyListAdapter parentRecyclerView;
        public TextView mTextView;
        public DataViewHolder(TextView v, PropertyListAdapter parent) {
            super(v);
            this.parentRecyclerView = parent;
            mTextView = v;
            mTextView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    parentRecyclerView.select(DataViewHolder.this);
                }
            });
        }
    }

    public PropertyListAdapter(Activity parent) {
        this.parent = parent;
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public PropertyListAdapter(List<Stringifyer> data) {
        this.data = data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PropertyListAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem, parent, false);
        DataViewHolder vh = new DataViewHolder(v, this);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(this.data.get(position).getReadableString());

    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public void addItem(final String key, final String value) {
        this.data.add(new Stringifyer() {
            @Override
            public String getReadableString() {
                return key + ": " + value;
            }
        });
    }

    public void select(DataViewHolder selected) {
        if(this.lastSelected != null) {
            this.lastSelected.mTextView.setSelected(false);
            this.lastSelected.mTextView.setBackgroundColor(this.parent.getResources()
                    .getColor(R.color.app_dark_button_background));
        }
        this.lastSelected = selected;
        this.lastSelected.mTextView.setSelected(true);
        this.lastSelected.mTextView.setBackgroundColor(this.parent.getResources()
                .getColor(android.R.color.holo_blue_light));
    }

    public DataViewHolder getLastSelected() {
        return this.lastSelected;
    }
}
