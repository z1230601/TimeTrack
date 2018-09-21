package android.finite.com.timetrack.view.spinner;

import android.app.Activity;
import android.finite.com.data.Customer;
import android.finite.com.timetrack.R;
import android.finite.com.timetrack.data.DataManager;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerSpinnerAdapter extends ArrayAdapter<Customer> implements CustomSpinnerAdapter {

    private final Map<Integer, Customer> customers = new HashMap<Integer, Customer>();

    public CustomerSpinnerAdapter(Activity base) {
        super(base.getBaseContext(), R.layout.spinnerlayout, R.id.spinnerItem);
        List<Customer> possibleCountries = DataManager.get().getCustomers();
        for(Customer customer : possibleCountries) {
            add(customer);
            this.customers.put(customer.getCustomerId(), customer);
        }
    }

    @Override
    public String getValueByDatabaseId(int id) {
        if(this.customers.containsKey(id)){
            return this.customers.get(id).toString();
        }
        return "";
    }

    @Override
    public int getIndex(int countryId) {
        for(int i=0; i < getCount(); i ++){
            if(getItem(i).getCustomerId() == countryId) {
                return i;
            }
        }
        return 0;
    }
}
