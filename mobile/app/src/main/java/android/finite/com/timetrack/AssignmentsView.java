package android.finite.com.timetrack;

import android.finite.com.data.Assignment;
import android.finite.com.data.Project;
import android.finite.com.timetrack.data.DataManager;
import android.finite.com.timetrack.view.AssignmentsListAdapter;
import android.finite.com.timetrack.view.DrawerListener;
import android.finite.com.timetrack.view.cards.AssignmentCard;
import android.finite.com.timetrack.view.cards.BaseCardView;
import android.finite.com.timetrack.view.cards.CardSelector;
import android.finite.com.timetrack.view.cards.ProjectCard;
import android.finite.com.timetrack.view.spinner.ProjectSpinnerAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class AssignmentsView extends AppCompatActivity implements CardSelector{

    private LinearLayoutManager layoutManager;
    private RecyclerView assigmentList;
    private AssignmentsListAdapter adapater;
    private Spinner currentProjectSpinner;
    private AssignmentCard selectedCard;
    private Button makeCurrentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.layoutManager = new LinearLayoutManager(this);
        this.assigmentList = (RecyclerView) findViewById(R.id.assigmentList);
        this.adapater = new AssignmentsListAdapter((CardSelector) this);
        this.assigmentList.setAdapter(this.adapater);
        this.assigmentList.setLayoutManager(this.layoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addAssignmentFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        this.makeCurrentButton = (Button) findViewById(R.id.assignmentMakeCurrent);
        this.makeCurrentButton.setEnabled(false);
        this.makeCurrentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.get().setCurrentProject((Project) currentProjectSpinner.getSelectedItem());
                DataManager.get().setCurrentAssigment(selectedCard.getAssociatedAssignment());
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        this.currentProjectSpinner = (Spinner) findViewById(R.id.assignmentProjectSelector);
        currentProjectSpinner.setAdapter(new ProjectSpinnerAdapter(this));
        currentProjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reloadAssigments();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new DrawerListener(this));
    }

    private void reloadAssigments() {
        Project project = (Project) this.currentProjectSpinner.getSelectedItem();
        this.adapater.setProject(project.getProjectId());

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
        getMenuInflater().inflate(R.menu.assignments, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setSelectedCard(BaseCardView card) {
        if(!(card instanceof AssignmentCard)) {
            return;
        }
        AssignmentCard selectedCard = (AssignmentCard) card;

        if(this.selectedCard != null) {
            this.selectedCard.resetSelection();
        }

        if(this.selectedCard == selectedCard){
            this.selectedCard = null;
        } else {
            this.selectedCard = selectedCard;
        }

        if ( this.selectedCard == null ) {
            this.makeCurrentButton.setEnabled(false);
        } else {
            this.selectedCard.setCardBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            this.makeCurrentButton.setEnabled(true);
        }
    }
}
