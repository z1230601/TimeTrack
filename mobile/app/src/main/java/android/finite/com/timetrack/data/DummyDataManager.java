package android.finite.com.timetrack.data;

import android.finite.com.data.Country;
import android.finite.com.data.Customer;
import android.finite.com.data.Project;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Currency;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DummyDataManager extends DataManager {
    Map<Integer, Project> projects = new LinkedHashMap<>();
    Map<Integer, Country> countries = new LinkedHashMap<>();
    Map<Integer, Customer> customers = new LinkedHashMap<>();
    private int projectId = 0;
    private int currentCustomerId = 0;
    private int currentCountryId = 0;

    public DummyDataManager() {
        super();
        initProjectSampleData();
        initSampleCountries();
        initSampleCustomers();
    }

    private void initSampleCustomers() {
        {
            Customer customer = new Customer(this.currentCustomerId, "SOME FANCY NAME d.o.o", "Wienerstraße 27, 1040 Wien",
                    "SOME FANCY NAME d.o.o", "Wienerstraße 27, 1040 Wien",
                    "0043 664 555 555 55");
            this.customers.put(this.currentCustomerId, customer);
        }
        this.currentCustomerId++;
        {
            Customer customer = new Customer(this.currentCustomerId, "Training LTD", "Appstreet 39, 90923932 Yangon",
                    "FT LTD", "Appstreet 39, 90923932 Yangon",
                    "0256 66 6565 65656");
            this.customers.put(this.currentCustomerId, customer);
        }
        this.currentCustomerId++;
        {
            Customer customer = new Customer(this.currentCustomerId, "Swiss GmbH", "Züricherstraße 15, 9092 Genf",
                    "Swiss GmbH", "Züricherstraße 39, 9092 Genf",
                    "001 66 6565 65656");
            this.customers.put(this.currentCustomerId, customer);
        }
        this.currentCustomerId++;
        {
            Customer customer = new Customer(this.currentCustomerId, "Some Company LTD", "OnotherStreet 39, 90923932 Seattle",
                    "Some Company LTD", "OnotherStreet 39, 90923932 Seattle",
                    "0256 66 6565 65656");
            this.customers.put(this.currentCustomerId, customer);
        }
        this.currentCustomerId++;
    }

    private void initSampleCountries() {
        {
            Country country = new Country(this.currentCountryId, "Germany",
                    new LatLng(22.147920, 96.059790),Currency.getInstance("MMK"));
            countries.put(this.currentCountryId, country);
        }
        this.currentCountryId++;
        {
            Country country = new Country(this.currentCountryId, "Austria",
                    new LatLng(47.376656, 14.180806),Currency.getInstance("EUR"));
            countries.put(this.currentCountryId, country);
        }
        this.currentCountryId++;
        {
            Country country = new Country(this.currentCountryId, "China",
                    new LatLng(22.440366, 78.717554),Currency.getInstance("INR"));
            countries.put(this.currentCountryId, country);
        }
        this.currentCountryId++;
        {
            Country country = new Country(this.currentCountryId, "Australia",
                    new LatLng(-24.793458, 133.714829),Currency.getInstance("AUD"));
            countries.put(this.currentCountryId, country);
        }
        this.currentCountryId++;
    }

    public void initProjectSampleData() {
        {
            Project proj = new Project("Dog",this.projectId, 0, 0);
            proj.setAdditionalProperty("Aircraftype", "ATR72-500");
            proj.setAdditionalProperty("Country", "Germany");
            projects.put(this.projectId, proj);
        }
        this.projectId++;
        {
            Project proj = new Project("Hamster",this.projectId, 1, 1);
            proj.setAdditionalProperty("Aircraftype", "ATR72-500");
            proj.setAdditionalProperty("Country", "Myanmar");
            projects.put(this.projectId, proj);
        }
        this.projectId++;
        {
            Project proj = new Project("Jojo",this.projectId, 2, 2);
            proj.setAdditionalProperty("Aircraftype", "ATR72-600");
            proj.setAdditionalProperty("Country", "China");
            projects.put(this.projectId, proj);
        }
        this.projectId++;
        {
            Project proj = new Project("Kondor",this.projectId, 3, 3);
            proj.setAdditionalProperty("Aircraftype", "ATR72-600");
            proj.setAdditionalProperty("Country", "Australia");
            projects.put(this.projectId, proj);
        }
        this.projectId++;
        {
            Project proj = new Project("Laprados",this.projectId, 3, 3);
            proj.setAdditionalProperty("Aircraftype", "CJ1+");
            proj.setAdditionalProperty("Country", "Austria");
            projects.put(this.projectId, proj);
        }
        this.projectId++;
    }

    @Override
    public List<Project> getProjects() {
        return new ArrayList<Project>(this.projects.values());
    }

    @Override
    public Project getProjectById(final int projectId) {
        return this.projects.get(projectId);
    }

    public List<Country> getCountries() {
        return new ArrayList<Country>(this.countries.values());
    }

    @Override
    public Country getCountryById(int associatedCountry) {
        return this.countries.get(associatedCountry);
    }

    @Override
    public List<Customer> getCustomers() {
        return new ArrayList<Customer>(this.customers.values());
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return this.customers.get(customerId);
    }


    public void saveNewProject(Project project) {
        project.setProjectId(this.projectId);
        this.projects.put(this.projectId, project);
        this.projectId++;
    }

    public void updateProject(Project project) {
        this.projects.put(project.getProjectId(), project);
    }
}
