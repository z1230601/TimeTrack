package android.finite.com.timetrack.data;

import android.finite.com.data.Assignment;
import android.finite.com.data.Country;
import android.finite.com.data.Customer;
import android.finite.com.data.Project;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager instance = null;
    protected Project currentProject;
    protected Assignment currentAssignment;

    protected DataManager() {}

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
    public List<Country> getCountries() { return new ArrayList<Country>();}
    public List<Customer> getCustomers() {return new ArrayList<Customer>();}

    public Project getProjectById(final int projectId) {
        return new Project();
    }

    public Country getCountryById(int associatedCountry) {
        return null;
    }

    public Customer getCustomerById(int customerId) {
        return null;
    }


    public void saveNewProject(Project project) {
    }

    public void updateProject(Project project) {
    }

    public ArrayList<Assignment> getAssignments() {
        return new ArrayList<Assignment>();
    }

    public List<Assignment> getAssignmentsForProject(int projectId) { return new ArrayList<Assignment>();}

    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }

    public void setCurrentAssignment(Assignment currentAssignment) {
        this.currentAssignment = currentAssignment;
    }

    public List<Assignment> getCurrentAssigments() {
        return this.getAssignmentsForProject(this.currentProject.getProjectId());
    }

    public Assignment getSelectedAssignment() {
        return this.currentAssignment;
    }

    public Project getSelectedProject() {
        return this.currentProject;
    }
}
