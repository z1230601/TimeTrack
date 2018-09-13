package android.finite.com.timetrack.view;

import android.app.Activity;
import android.content.Intent;
import android.finite.com.timetrack.ProjectsView;
import android.finite.com.timetrack.R;
import android.finite.com.timetrack.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

public class DrawerListener implements NavigationView.OnNavigationItemSelectedListener {
    private final Activity parent;

    public DrawerListener(Activity parent) {
        this.parent = parent;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_projects) {
            Intent intent = new Intent();
            intent.setClass(this.parent, ProjectsView.class);
            parent.startActivity(intent);
        } else if (id == R.id.nav_assignments) {

        } else if (id == R.id.nav_times) {

        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent();
            intent.setClass(this.parent, Settings.class);
            parent.startActivity(intent);
        } else if (id == R.id.nav_accommodations) {

        } else if (id == R.id.nav_flights) {

        }

        DrawerLayout drawer = (DrawerLayout) parent.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
