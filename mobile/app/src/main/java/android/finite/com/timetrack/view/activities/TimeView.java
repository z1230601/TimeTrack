package android.finite.com.timetrack.view.activities;

import android.content.Intent;
import android.finite.com.timetrack.R;
import android.finite.com.timetrack.TimeAddActivity;
import android.finite.com.timetrack.db.data.Assignment;
import android.finite.com.timetrack.db.data.TimeEntry;
import android.finite.com.timetrack.data.DataManager;
import android.finite.com.timetrack.view.cards.GenericCardView;
import android.finite.com.timetrack.view.listener.DrawerListener;
import android.finite.com.timetrack.view.cards.AssigmentCardListAdapter;
import android.finite.com.timetrack.view.cards.TimeCardListAdapter;
import android.finite.com.timetrack.view.cards.CardSelector;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class TimeView extends AppCompatActivity implements CardSelector {

    private static final int TIME_ADD_REQUEST_CODE = 1024;

    private LinearLayoutManager layoutManager;
    private RecyclerView timeList;
    private AssigmentCardListAdapter adapater;
    private TimeCardListAdapter cardListAdapter;
    private Assignment selected;
    private GenericCardView selectedCard;

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
                Intent intent = new Intent();
                intent.setClass(view.getContext(), TimeAddActivity.class);
                startActivityForResult(intent, TIME_ADD_REQUEST_CODE);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new DrawerListener(this));
        this.selected = DataManager.get().getSelectedAssignment();
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
        getMenuInflater().inflate(R.menu.time_view, menu);
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
    public void setSelectedCard(CardView card, Object associacation) {
//        if(associacation instanceof TimeEntry){
//            if(this.selectedCard != null) {
//                this.selectedCard.resetSelection();
//            }
//
//            if(this.selectedCard == card){
//                this.selectedCard = null;
//            } else {
//                this.selectedCard = (GenericCardView) card;
//            }
//            if ( this.selectedCard == null ) {
//               // findViewById(R.id.editFab).setVisibility(View.GONE);
//            } else {
//                //findViewById(R.id.editFab).setVisibility(View.VISIBLE);
//                this.selectedCard.setCardBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
//            }
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("TimeView", "Got request Code " + requestCode);
        if(requestCode == TIME_ADD_REQUEST_CODE) {
            init(selected);
        }
    }

}
