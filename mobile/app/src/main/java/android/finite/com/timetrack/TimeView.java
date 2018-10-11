package android.finite.com.timetrack;

import android.finite.com.data.Assignment;
import android.finite.com.data.TimeEntry;
import android.finite.com.timetrack.data.DataManager;
import android.finite.com.timetrack.view.DrawerListener;
import android.finite.com.timetrack.view.AssigmentCardListAdapter;
import android.finite.com.timetrack.view.TimeCardListAdapter;
import android.finite.com.timetrack.view.cards.CardSelector;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
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
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class TimeView extends AppCompatActivity implements CardSelector {

    private LinearLayoutManager layoutManager;
    private RecyclerView timeList;
    private AssigmentCardListAdapter adapater;
    private TimeCardListAdapter cardListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.timeAddFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new DrawerListener(this));
        Assignment selected = DataManager.get().getSelectedAssignment();
        init(selected);
    }

    private void init(Assignment selected) {
        TextView heading = (TextView) findViewById(R.id.assignmentName);
        heading.setText(selected.getShortName());

        DateFormat dayFormat = new SimpleDateFormat("dd.MM");
        DateFormat yearFormat = new SimpleDateFormat("yyyy");

        TextView from = findViewById(R.id.fromDate);
        TextView to = findViewById(R.id.toDate);
        TextView year = findViewById(R.id.yearText);

        from.setText(dayFormat.format(selected.getFromDate()));
        to.setText(dayFormat.format(selected.getToDate()));
        if(selected.getToDate().getYear() == selected.getToDate().getYear()){
            year.setText(yearFormat.format(selected.getToDate()));
        }else {
            year.setText(yearFormat.format(selected.getFromDate())
                    + "/" + yearFormat.format(selected.getToDate()));
        }

        this.cardListAdapter = new TimeCardListAdapter(this);
        this.cardListAdapter.setAssignment(selected);

        this.layoutManager = new LinearLayoutManager(this);
        this.timeList = (RecyclerView) findViewById(R.id.timeList);
        this.timeList.setAdapter(this.cardListAdapter);
        this.timeList.setLayoutManager(this.layoutManager);
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
        getMenuInflater().inflate(R.menu.time_view, menu);
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

    @Override
    public void setSelectedCard(CardView cardView, Object associacation) {
        if(associacation instanceof TimeEntry){

        }
    }
}
