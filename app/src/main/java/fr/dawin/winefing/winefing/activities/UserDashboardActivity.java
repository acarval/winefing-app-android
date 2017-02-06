package fr.dawin.winefing.winefing.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.User;
import fr.dawin.winefing.winefing.fragments.DiscoverFragment;
import fr.dawin.winefing.winefing.fragments.UserDashboardFragment;

public class UserDashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    protected android.app.FragmentManager fragmentManager = getFragmentManager();

    private static final String TAG = "UserDashboardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getFragmentManager().getBackStackEntryCount() == 0) finish();
            }
        });

        setContentView(R.layout.activity_user_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentTransaction tx = fragmentManager.beginTransaction();
        tx.replace(R.id.content_frame, new UserDashboardFragment())
            .addToBackStack(TAG)
            .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        Bundle extra = getIntent().getExtras();
        User user = extra.getParcelable("user");

        View v = navigationView.getHeaderView(0);
        TextView tv_userName = (TextView ) v.findViewById(R.id.textView_username);
        tv_userName.setText(user.getPrenom() + " " + user.getNom());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_home);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        FragmentTransaction tx = fragmentManager.beginTransaction();

        if (id == R.id.nav_home) {
            tx.replace(R.id.content_frame, new UserDashboardFragment(), TAG)
                .addToBackStack(TAG)
                .commit();

        } else if (id == R.id.nav_profile) {


        } else if (id == R.id.nav_booking) {


        } else if (id == R.id.nav_discover) {
            tx.replace(R.id.content_frame, new DiscoverFragment(), TAG)
                .addToBackStack(TAG)
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
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_booking);
    }

    public void propertiesList(View view) {
        Log.e(TAG, "properties");
        FragmentTransaction tx = fragmentManager.beginTransaction();
        tx.replace(R.id.content_frame, new DiscoverFragment(), TAG)
            .addToBackStack(TAG)
            .commit();

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_discover);
    }
}