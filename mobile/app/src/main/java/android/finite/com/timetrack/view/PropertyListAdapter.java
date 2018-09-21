package android.finite.com.timetrack.view;

import android.finite.com.timetrack.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyListAdapter.DataViewHolder> {
    private List<Stringifyer> data = new ArrayList<>();


    public static class DataViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public DataViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public PropertyListAdapter() {
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
        DataViewHolder vh = new DataViewHolder(v);
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
}
