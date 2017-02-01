package fr.dawin.winefing.winefing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class UserDashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    protected android.app.FragmentManager fragmentManager = getFragmentManager();

    private static final String TAG = "UserDashboardActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, new UserDashboardFragment())
                .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle extra = getIntent().getExtras();
        User user = extra.getParcelable("user");

        View v = navigationView.getHeaderView(0);
        TextView tv_userName = (TextView ) v.findViewById(R.id.textView_username);
        tv_userName.setText(user.getPrenom() + " " + user.getNom());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button_red, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new UserDashboardFragment())
                    .commit();

        } else if (id == R.id.nav_profile) {


        } else if (id == R.id.nav_booking) {


        } else if (id == R.id.nav_discover) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new DiscoverFragment())
                    .commit();

        } else if (id == R.id.nav_winelist) {


        } else if (id == R.id.nav_disconnect) {
            SharedPreferences loginPreferences;
            SharedPreferences.Editor loginPrefsEditor;
            loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
            loginPrefsEditor = loginPreferences.edit();
            loginPrefsEditor.clear();
            loginPrefsEditor.commit();

            Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void bookingList(View view) {
        Log.e(TAG, "booking");
    }

    public void propertiesList(View view) {
        Log.e(TAG, "properties");
    }
}
