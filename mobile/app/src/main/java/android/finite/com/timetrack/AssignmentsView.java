package android.finite.com.timetrack;

import android.finite.com.data.Assignment;
import android.finite.com.data.Project;
import android.finite.com.timetrack.data.DataManager;
import android.finite.com.timetrack.view.AssignmentsListAdapter;
import android.finite.com.timetrack.view.DrawerListener;
import android.finite.com.timetrack.view.spinner.ProjectSpinnerAdapter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.List;

public class AssignmentsView extends AppCompatActivity {

    private LinearLayoutManager layoutManager;
    private RecyclerView assigmentList;
    private AssignmentsListAdapter adapater;
    private Spinner currentProjectSpinner;

    private AdapterView.OnItemClickListener projectSwitchListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(currentProjectSpinner != null) {
                Project item = (Project) currentProjectSpinner.getItemAtPosition(position);
                reloadAssigments(item.getProjectId());
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.layoutManager = new LinearLayoutManager(this);
        this.assigmentList = (RecyclerView) findViewById(R.id.assigmentList);
        this.adapater = new AssignmentsListAdapter();
        this.assigmentList.setAdapter(this.adapater);

//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addAssignmentFab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        this.currentProjectSpinner = (Spinner) findViewById(R.id.assignmentProjectSelector);
        currentProjectSpinner.setAdapter(new ProjectSpinnerAdapter(this));
        currentProjectSpinner.setOnItemClickListener(this.projectSwitchListener);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new DrawerListener(this));
    }

    private void reloadAssigments(int projectId) {
        List<Assignment> assigments = DataManager.get().getAssignmentsForProject(projectId);

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
        getMenuInflater().inflate(R.menu.assignments, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
