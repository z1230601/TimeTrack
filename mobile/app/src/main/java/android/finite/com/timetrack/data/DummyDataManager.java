package android.finite.com.timetrack.data;

import android.finite.com.data.Country;
import android.finite.com.data.Customer;
import android.finite.com.data.Project;
import android.location.Address;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Currency;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DummyDataManager extends DataManager {
    List<Project> projects = new ArrayList<Project>();
    Map<Integer, Country> countries = new LinkedHashMap<>();
    Map<Integer, Customer> customers = new LinkedHashMap<>();

    public DummyDataManager() {
        super();
        initProjectSampleData();
        initSampleCountries();
        initSampleCustomers();
    }

    private void initSampleCustomers() {
        {
            int id = 0;
            Customer customer = new Customer(id, "SOME FANCY NAME d.o.o", "Wienerstraße 27, 1040 Wien",
                    "SOME FANCY NAME d.o.o", "Wienerstraße 27, 1040 Wien",
                    "0043 664 555 555 55");
            this.customers.put(id, customer);
        }
        {
            int id = 1;
            Customer customer = new Customer(id, "Training LTD", "Appstreet 39, 90923932 Yangon",
                    "FT LTD", "Appstreet 39, 90923932 Yangon",
                    "0256 66 6565 65656");
            this.customers.put(id, customer);
        }
        {
            int id = 2;
            Customer customer = new Customer(id, "Swiss GmbH", "Züricherstraße 15, 9092 Genf",
                    "Swiss GmbH", "Züricherstraße 39, 9092 Genf",
                    "001 66 6565 65656");
            this.customers.put(id, customer);
        }
        {
            int id = 3;
            Customer customer = new Customer(id, "Some Company LTD", "OnotherStreet 39, 90923932 Seattle",
                    "Some Company LTD", "OnotherStreet 39, 90923932 Seattle",
                    "0256 66 6565 65656");
            this.customers.put(id, customer);
        }
    }

    private void initSampleCountries() {
        {
            Country country = new Country(0, "Germany",
                    new LatLng(22.147920, 96.059790),Currency.getInstance("MMK"));
            countries.put(0, country);
        }
        {
            Country country = new Country(1, "Austria",
                    new LatLng(47.376656, 14.180806),Currency.getInstance("EUR"));
            countries.put(1, country);
        }
        {
            Country country = new Country(2, "China",
                    new LatLng(22.440366, 78.717554),Currency.getInstance("INR"));
            countries.put(2, country);
        }
        {
            Country country = new Country(3, "Australia",
                    new LatLng(-24.793458, 133.714829),Currency.getInstance("AUD"));
            countries.put(3, country);
        }
    }

    public void initProjectSampleData() {
        {
            Project proj = new Project("Dog",0, 0, 0);
            proj.setAdditionalProperty("Aircraftype", "ATR72-500");
            proj.setAdditionalProperty("Country", "Germany");
            projects.add(proj);
        }
        {
            Project proj = new Project("Hamster",1, 1, 1);
            proj.setAdditionalProperty("Aircraftype", "ATR72-500");
            proj.setAdditionalProperty("Country", "Myanmar");
            projects.add(proj);
        }
        {
            Project proj = new Project("Jojo",2, 2, 2);
            proj.setAdditionalProperty("Aircraftype", "ATR72-600");
            proj.setAdditionalProperty("Country", "China");
            projects.add(proj);
        }
        {
            Project proj = new Project("Kondor",3, 3, 3);
            proj.setAdditionalProperty("Aircraftype", "ATR72-600");
            proj.setAdditionalProperty("Country", "Australia");
            projects.add(proj);
        }
        {
            Project proj = new Project("Laprados",4, 3, 3);
            proj.setAdditionalProperty("Aircraftype", "CJ1+");
            proj.setAdditionalProperty("Country", "Austria");
            projects.add(proj);
        }
    }

    @Override
    public List<Project> getProjects() {
        return this.projects;
    }

    @Override
    public Project getProjectById(final int projectId) {
        for(Project proj : this.projects) {
            if(projectId == proj.getProjectId()){
                return proj;
            }
        }
        return null;
    }

    @Override
    public Country getCountryById(int associatedCountry) {
        return this.countries.get(associatedCountry);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return this.customers.get(customerId);
    }
}
