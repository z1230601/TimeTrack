package android.finite.com.timetrack;

import android.content.Intent;
import android.finite.com.timetrack.controller.Controllers;
import android.finite.com.timetrack.controller.TimeHandler;
import android.finite.com.timetrack.db.data.Assignment;
import android.finite.com.timetrack.db.data.Project;
import android.finite.com.timetrack.db.data.User;
import android.finite.com.timetrack.data.DataManager;
import android.finite.com.timetrack.data.DummyDataManager;
import android.finite.com.timetrack.view.StartStopButtonLogic;
import android.finite.com.timetrack.view.listener.DrawerListener;
import android.finite.com.timetrack.view.spinner.AssigmentSpinnerAdapter;
import android.finite.com.timetrack.view.spinner.ProjectSpinnerAdapter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class TimeTrackHome extends AppCompatActivity {
    private User currentuser = null;
    private Spinner currentProjectSpinner;
    private Spinner currentAssignmentSpinner;
    private AssigmentSpinnerAdapter assignmentAdapter;
    private ProjectSpinnerAdapter projectAdapater;
    private StartStopButtonLogic startStopLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(! (DataManager.get() instanceof DummyDataManager)) {
            DataManager.insertInstance(new DummyDataManager());
        }

        setContentView(R.layout.activity_time_track_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new DrawerListener(this));

        this.currentProjectSpinner = (Spinner) findViewById(R.id.currentProject);
        this.projectAdapater = new ProjectSpinnerAdapter(this);
        this.currentProjectSpinner.setAdapter(this.projectAdapater);
        this.currentProjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Project selectedProject = (Project) currentProjectSpinner.getItemAtPosition(position);
                DataManager.get().setCurrentProject(selectedProject);
                initAssignemnts();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        this.currentAssignmentSpinner = (Spinner) findViewById(R.id.currentAssignment);
        this.assignmentAdapter = new AssigmentSpinnerAdapter(this);
        this.currentAssignmentSpinner.setAdapter(this.assignmentAdapter);
        this.currentAssignmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DataManager.get().setCurrentAssignment((Assignment) currentAssignmentSpinner.getSelectedItem());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Project selectProject = DataManager.get().getSelectedProject();
        Assignment selectedAssignment = DataManager.get().getSelectedAssignment();

        System.out.println("Project " + selectProject + ": contains " +
                this.projectAdapater.getPosition(selectProject));

        this.currentProjectSpinner.setSelection(this.projectAdapater
                .getPosition(selectProject));
        this.currentAssignmentSpinner.setSelection(this.assignmentAdapter
                .getPosition(selectedAssignment));

        //TODO implement start to timehandler controller
        initStartStop();

    }

    private void initStartStop() {
        Button start = (Button) findViewById(R.id.startStopBtn);
        this.startStopLogic = new StartStopButtonLogic(start,
                Controllers.get().getController(TimeHandler.class), this);
    }

    private void initAssignemnts() {
        this.assignmentAdapter.init();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.time_track_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent();
            intent.setClass(this, Settings.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
