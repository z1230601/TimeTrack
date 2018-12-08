package android.finite.com.timetrack.view.spinner;

import android.app.Activity;
import android.finite.com.timetrack.db.data.Country;
import android.finite.com.timetrack.R;
import android.finite.com.timetrack.data.DataManager;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountrySpinnerAdapter extends ArrayAdapter<Country> implements CustomSpinnerAdapter {
    private final Map<Integer, Country> countries = new HashMap<Integer, Country>();

    public CountrySpinnerAdapter(Activity base) {
        super(base.getBaseContext(), R.layout.spinnerlayout, R.id.spinnerItem);

        List<Country> possibleCountries = DataManager.get().getCountries();
        for(Country country : possibleCountries) {
            add(country);
            this.countries.put(country.getCountryId(), country);
        }
    }

    @Override
    public String getValueByDatabaseId(int id) {
        if(this.countries.containsKey(id)){
            return this.countries.get(id).toString();
        }
        return "";
    }

    @Override
    public int getIndex(int countryId) {
        for(int i=0; i < getCount(); i ++){
            if(getItem(i).getCountryId() == countryId) {
                return i;
            }
        }
        return 0;
    }
}
