package android.finite.com.timetrack.data;

import android.finite.com.data.Country;
import android.finite.com.data.Customer;
import android.finite.com.data.Project;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager instance = null;

    protected DataManager() {

    }

    public static void insertInstance(DataManager manager) {
        instance = manager;
    }

    public static DataManager get() {
        if(instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public List<Project> getProjects() {
        return new ArrayList<Project>();
    }

    public Project getProjectById(final int projectId) {
        return new Project();
    }

    public Country getCountryById(int associatedCountry) {
        return null;
    }

    public Customer getCustomerById(int customerId) {
        return null;
    }
}
