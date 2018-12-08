package android.finite.com.timetrack.view.properties;

import android.app.Activity;
import android.finite.com.timetrack.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyListAdapter.DataViewHolder> {
    private List<String> keyOrder;
    private Activity parent;
    private Map<String, String> data;
    private DataViewHolder lastSelected;

    public void setData(Map<String, String> properties) {
        this.data = properties;
        this.keyOrder = new ArrayList<>(this.data.keySet());
        notifyDataSetChanged();
    }


    public static class DataViewHolder extends RecyclerView.ViewHolder {
        private PropertyListAdapter parentRecyclerView;
        public TextView mTextView;
        private String propertyKey;

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

        public void setPropertyKey(String propertyKey) {
            this.propertyKey = propertyKey;
        }

        public String getPropertyKey() {
            return this.propertyKey;
        }
    }

    public PropertyListAdapter(Activity parent, Map<String,String> properties) {
        this.parent = parent;
        this.data = properties;
        this.keyOrder = new ArrayList<>(this.data.keySet());
    }

    public PropertyListAdapter(Activity parent, Map<String,String> properties, List<String> keyOrder) {
        this.parent = parent;
        this.data = properties;
        this.keyOrder = keyOrder;
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
        holder.mTextView.setText(this.keyOrder.get(position) + ":" + this.data.get(this.keyOrder.get(position)));
        holder.setPropertyKey(this.keyOrder.get(position));

    }

    @Override
    public int getItemCount() {
        return this.data.size();
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
