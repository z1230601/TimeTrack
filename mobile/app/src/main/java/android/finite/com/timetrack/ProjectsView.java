package android.finite.com.timetrack;

import android.content.Intent;
import android.finite.com.data.Project;
import android.finite.com.timetrack.data.DataManager;
import android.finite.com.timetrack.view.cards.GenericCardView;
import android.finite.com.timetrack.view.cards.CardSelector;
import android.finite.com.timetrack.view.DrawerListener;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ProjectsView extends AppCompatActivity implements CardSelector {

    private LinearLayout leftCards;
    private LinearLayout rightCards;
    private GenericCardView selectedCard = null;
    private List<GenericCardView> projectCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new DrawerListener(this));

        this.leftCards = (LinearLayout) findViewById(R.id.PV_left_cards);
        this.rightCards = (LinearLayout) findViewById(R.id.PV_right_cards);

        createCards();

        {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addFab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(), ProjectDataView.class);
                    startActivity(intent);

                }
            });
        }
        {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.editFab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(), ProjectDataView.class);
                    intent.putExtra(Project.PROJECT_KEY, ((Project) selectedCard.getAssociatedDataProvder()).getProjectId());
                    startActivity(intent);

                }
            });
        }
    }

    private void createCards() {
        this.projectCards.clear();
        this.leftCards.removeAllViews();
        this.rightCards.removeAllViews();
        for(int i=0; i < DataManager.get().getProjects().size(); i++) {
            LinearLayout layout = (i % 2 == 0? leftCards : rightCards);
            GenericCardView card = new GenericCardView(layout.getContext(), (CardSelector) this, DataManager.get().getProjects().get(i));
            card.create();
            layout.addView(card);
            this.projectCards.add(card);
        }
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
        getMenuInflater().inflate(R.menu.projects_view, menu);
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

    public void setSelectedCard(GenericCardView card) {
        if(this.selectedCard != null) {
            this.selectedCard.resetSelection();
        }

        if(this.selectedCard == card){
            this.selectedCard = null;
        } else {
            this.selectedCard = card;
        }
        if ( this.selectedCard == null ) {
            findViewById(R.id.editFab).setVisibility(View.GONE);
        } else {
            findViewById(R.id.editFab).setVisibility(View.VISIBLE);
            this.selectedCard.setCardBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        createCards();
    }

}
