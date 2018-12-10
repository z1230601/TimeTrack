package android.finite.com.timetrack.data;

import android.finite.com.timetrack.db.data.Assignment;
import android.finite.com.timetrack.db.data.Country;
import android.finite.com.timetrack.db.data.Customer;
import android.finite.com.timetrack.db.data.Project;
import android.finite.com.timetrack.db.data.TimeEntry;
import android.finite.com.utility.Tuple;

import com.google.android.gms.maps.model.LatLng;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DummyDataManager extends DataManager {
    Map<Integer, Project> projects = new LinkedHashMap<>();
    Map<Integer, Country> countries = new LinkedHashMap<>();
    Map<Integer, Customer> customers = new LinkedHashMap<>();
    Map<Integer, Assignment> assignments = new LinkedHashMap<>();
    Map<Integer, List<Assignment>> projectAssigmentMapping = new LinkedHashMap<>();
    Map<Integer, List<TimeEntry>> times = new LinkedHashMap<>();

    private int currentProjectId = 0;
    private int currentCustomerId = 0;
    private int currentCountryId = 0;
    private int currentAssignmentId = 0;
    private int currentTimeId = 0;

    public DummyDataManager() {
        super();
        initProjectSampleData();
        initSampleCountries();
        initSampleCustomers();
        initAssigments();
        initTimes();
        this.currentProject = this.projects.get(0);
        this.currentAssignment = this.projectAssigmentMapping.get(0).get(0);
    }

    private void initTimes() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<TimeEntry> entries = new ArrayList<>();
        {
            TimeEntry entry = null;
            try {
                entry = new TimeEntry(currentTimeId,
                        format.parse("2018-03-23"),
                        new GregorianCalendar(2018, 03,23,07,32,55).getTime(),
                        new GregorianCalendar(2018, 03,23,19,45,05).getTime(),
                        TimeEntry.Type.TRAVEL_PASSIV,
                        0,
                        new ArrayList<Tuple<Date, Date>>(){{
                            add(new Tuple<Date, Date>(
                                    new GregorianCalendar(2018, 03,23,11,30,00).getTime(),
                                    new GregorianCalendar(2018, 03,23,12,00,00).getTime()
                            ));
                        }});
            } catch (ParseException e) {
                e.printStackTrace();
            }
            currentTimeId++;
            entries.add(entry);
        }
        {
            TimeEntry entry = null;
            try {
                entry = new TimeEntry(currentTimeId,
                        format.parse("2018-03-25"),
                        new GregorianCalendar(2018, 03,24,10,15,19).getTime(),
                        new GregorianCalendar(2018, 03,25,2,37,6).getTime(),
                        TimeEntry.Type.TRAVEL_ACTIVE,
                        0,
                        new ArrayList<Tuple<Date, Date>>(){{
                            add(new Tuple<Date, Date>(
                                    new GregorianCalendar(2018, 03,24,16,30,00).getTime(),
                                    new GregorianCalendar(2018, 03,24,17,00,00).getTime()
                            ));
                        }});
            } catch (ParseException e) {
                e.printStackTrace();
            }
            currentTimeId++;
            entries.add(entry);
        }
        {
            TimeEntry entry = null;
            try {
                entry = new TimeEntry(currentTimeId,
                        format.parse("2018-03-25"),
                        new GregorianCalendar(2018, 03,25,12,00,55).getTime(),
                        new GregorianCalendar(2018, 03,25,22,45,05).getTime(),
                        TimeEntry.Type.WORK,
                        0,
                        new ArrayList<Tuple<Date, Date>>(){{
                            add(new Tuple<Date, Date>(
                                    new GregorianCalendar(2018, 03,25,17,30,00).getTime(),
                                    new GregorianCalendar(2018, 03,25,17,45,00).getTime()
                            ));
                        }});
            } catch (ParseException e) {
                e.printStackTrace();
            }
            currentTimeId++;
            entries.add(entry);
        }

        this.times.put(0, entries);
    }

    private void initAssigments() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Assignment> projectAssigments = new ArrayList<>();
        {
            Assignment assign = null;
            try {
                assign = new Assignment(this.currentAssignmentId,
                        "Implement GUI",
                        "This task entails the implementation of the GUI of the Android Application.",
                        format.parse("2018-03-01"),
                        format.parse("2019-03-30"),
                        "Implement app view for the Android app.",
                        "",
                        "65464846186", 0);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            this.assignments.put(this.currentAssignmentId, assign);
            projectAssigments.add(assign);

        }
        this.currentAssignmentId++;
        {
            Assignment assign = null;
            try {
                assign = new Assignment(this.currentAssignmentId,
                        "Implement Database Backend",
                        "This assignment should produce a backend for the time tracking application.",
                        format.parse("2018-03-01"),
                        format.parse("2019-03-30"),
                        "",
                        "",
                        "65464846186",
                        0);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.assignments.put(this.currentAssignmentId, assign);
            projectAssigments.add(assign);
        }
        this.currentAssignmentId++;
        {
            Assignment assign = null;
            try {
                assign = new Assignment(this.currentAssignmentId,
                        "Testing",
                        "This assignment deals with thorough tests of the mobile application.",
                        format.parse("2018-03-01"),
                        format.parse("2019-03-30"),
                        "",
                        "",
                        "1234", 0);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.assignments.put(this.currentAssignmentId, assign);
            projectAssigments.add(assign);
        }
        this.currentAssignmentId++;

        this.projectAssigmentMapping.put(0, projectAssigments);
    }

    private void initSampleCustomers() {
        {
            Customer customer = new Customer(this.currentCustomerId, "TEST 1", "Wienerstraße 27, 1040 Wien",
                    "Test1 e.U.", "Wienerstraße 27, 1040 Wien",
                    "0043 664 555 555 55");
            this.customers.put(this.currentCustomerId, customer);
        }
        this.currentCustomerId++;
    }

    private void initSampleCountries() {
        {
            Country country = new Country(this.currentCountryId, "Austria",
                    new LatLng(47.376656, 14.180806),Currency.getInstance("EUR"));
            countries.put(this.currentCountryId, country);
        }
        this.currentCountryId++;
    }

    public void initProjectSampleData() {
        {
            Project proj = new Project("T2MobileApp",this.currentProjectId, 0, 0);
            proj.setAdditionalProperty("Target Platform", "Android");
            projects.put(this.currentProjectId, proj);
        }
        this.currentProjectId++;
        {
            Project proj = new Project("T2Server",this.currentProjectId, 0, 0);
            proj.setAdditionalProperty("Language", "C++");
            proj.setAdditionalProperty("Interfaces", "HTTP/TCP:JSON");
            projects.put(this.currentProjectId, proj);
        }
        this.currentProjectId++;
        {
            Project proj = new Project("T2Gui",this.currentProjectId, 0, 0);
            proj.setAdditionalProperty("Platforms", "Web & Desktop");
            projects.put(this.currentProjectId, proj);
        }
        this.currentProjectId++;
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
        project.setProjectId(this.currentProjectId);
        this.projects.put(this.currentProjectId, project);
        this.currentProjectId++;
    }

    @Override
    public void updateProject(Project project) {
        this.projects.put(project.getProjectId(), project);
    }

    @Override
    public ArrayList<Assignment> getAssignments() {
        return new ArrayList<Assignment>(this.assignments.values());
    }

    @Override
    public List<Assignment> getAssignmentsForProject(int projectId) {
        if(this.projectAssigmentMapping.containsKey(projectId)){
            return this.projectAssigmentMapping.get(projectId);
        }
        return super.getAssignmentsForProject(projectId);
    }

    public List<TimeEntry> getTimesForAssignment(int assignmentId) {
        return this.times.get(assignmentId);
    }

    public void saveNewTimeEntry(TimeEntry entry) {
        entry.setTimeId(this.currentTimeId);
        this.currentTimeId++;
        if(this.times.containsKey(this.getSelectedAssignment().getAssignmentId())) {
            this.times.get(this.getSelectedAssignment().getAssignmentId()).add(entry);
        }
    }
}
