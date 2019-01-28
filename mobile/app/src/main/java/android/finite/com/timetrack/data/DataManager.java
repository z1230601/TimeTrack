package android.finite.com.timetrack.data;

import android.finite.com.timetrack.db.DataAccess;
import android.finite.com.timetrack.db.data.Assignment;
import android.finite.com.timetrack.db.data.Country;
import android.finite.com.timetrack.db.data.Customer;
import android.finite.com.timetrack.db.data.Project;
import android.finite.com.timetrack.db.data.TimeEntry;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager instance = null;
    protected Project currentProject;
    protected Assignment currentAssignment;

    protected DataManager() {
//        if(getProjects().isEmpty()){
//            saveNewProject(new Project("Misc"));
//        }

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
        return DataAccess.getAppDatabase().projectDao().getAllProjects();
    }

    public void saveNewProject(Project project) {
        DataAccess.getAppDatabase().projectDao().saveProject(project);
    }

    public void updateProject(Project project) {
        DataAccess.getAppDatabase().projectDao().updateProjet(project);
    }

    public Project getProjectById(final int projectId) {
        return DataAccess.getAppDatabase().projectDao().getProjectById(projectId);
    }

    public void setSelectedProject(Project currentProject) {
        this.currentProject = currentProject;
    }

    public Project getSelectedProject() {
        return this.currentProject;
    }

    public List<Assignment> getAssignmentsForProject(int projectId) {
        return DataAccess.getAppDatabase().assignmentDao().getAssignmentsByProject(projectId);
    }

    public List<Assignment> getSelectedAssigments() {
        if(this.currentProject == null) {
            return new ArrayList<>();
        }
        return this.getAssignmentsForProject(this.currentProject.getProjectId());
    }

    public void setSelectedAssignment(Assignment currentAssignment) {
        this.currentAssignment = currentAssignment;
    }

    public Assignment getSelectedAssignment() {
        return this.currentAssignment;
    }

    public List<TimeEntry> getTimesForAssignment(int assignmentId) {
        return DataAccess.getAppDatabase().timeEntryDao().getTimesByAssignmentId(assignmentId);
    }

    public void saveNewTimeEntry(TimeEntry entry) {
        DataAccess.getAppDatabase().timeEntryDao().saveEntry(entry);
    }

    public List<Country> getCountries() {
        return DataAccess.getAppDatabase().countryDao().getAll();
    }

    public List<Customer> getCustomers() {
        return DataAccess.getAppDatabase().customerDao().getAll();
    }

    public Country getCountryById(int associatedCountry) {
        return DataAccess.getAppDatabase().countryDao().getCountryById(associatedCountry);
    }

    public Customer getCustomerById(int customerId) {
        return DataAccess.getAppDatabase().customerDao().getCustomerById(customerId);
    }
}
