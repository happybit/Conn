package me.pzheng.conn;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        // debug code
        // Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();

        Fragment fragment;
        switch (position) {
            case 0: //connect
                fragment = fm.findFragmentByTag(ConnectFragment.TAG);
                if (fragment == null) {
                    fragment = new ConnectFragment();
                }
                transaction.replace(R.id.container, fragment, ConnectFragment.TAG);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 1: //settings
                /* fragment = getFragmentManager().findFragmentByTag(StatsFragment.TAG);
                if (fragment == null) {
                    fragment = new StatsFragment();
                }
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, StatsFragment.TAG).commit(); */
                fragment = fm.findFragmentByTag(SettingsFragment.TAG);
                if (fragment == null) {
                    fragment = new SettingsFragment();
                }
                transaction.replace(R.id.container, fragment, SettingsFragment.TAG);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 2: //about
                fragment = fm.findFragmentByTag(AboutFragment.TAG);
                if (fragment == null) {
                    fragment = new AboutFragment();
                }
                transaction.replace(R.id.container, fragment, AboutFragment.TAG);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }

        //transaction.commit();

    }


    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
        } else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            Fragment fragment;
            fragment = fm.findFragmentByTag(SettingsFragment.TAG);
            if (fragment == null) {
                fragment = new SettingsFragment();
            }
            transaction.replace(R.id.container, fragment, SettingsFragment.TAG);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void dataOn(View view) {
        DataConn dataConnOn = new DataConn(this);
        dataConnOn.setMobileDataConnection(true);
   }

    public void dataOff(View view) {
        DataConn dataConnOff = new DataConn(this);
        dataConnOff.setMobileDataConnection(false);
    }

    public void onClickDataConn(View view) {
        KeepConnect keepConnect = new KeepConnect(this);
        keepConnect.execute();
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void onClickCredits(View view) {
        Intent intent = new Intent(this, DisplayCreditsActivity.class);
        startActivity(intent);
    }

    public void onClickLicense(View view) {
        Intent intent = new Intent(this, DisplayLicenseActivity.class);
        startActivity(intent);
    }

}
