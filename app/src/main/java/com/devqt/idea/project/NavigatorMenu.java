package com.devqt.idea.project;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import fragments.Android;
import fragments.Arduino;


public class NavigatorMenu extends AppCompatActivity {
    protected DrawerLayout mDrawer;
        Toolbar toolbar;
        ActionBarDrawerToggle   drawerToggle;
        Fragment                oneFragment     = new Android();
        Fragment                twoFragment     = new Arduino();
       // Fragment                threeFragment   = new LEGO();
      //  Fragment                fourFragment    = new STL();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.drawer_layout);

            addFragment(oneFragment);

            // Main Toolbar
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            //setSupportActionBar(toolbar);

            // Find our drawer view
            mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
            setupDrawerContent(nvDrawer);
            drawerToggle = setupDrawerToggle();
            mDrawer.addDrawerListener(drawerToggle);

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.toolbar_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            if (drawerToggle.onOptionsItemSelected(item)) {
                return true;
            }

            switch (item.getItemId()) {
                case R.id.action_toolbar_menu:
                    replaceFragment(twoFragment);
                    return true;

                case android.R.id.home:
                    mDrawer.openDrawer(GravityCompat.START);
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }

        // 'onPostCreate' called when activity start-up is complete after 'onStart()'
        // NOTE! Make sure to override the method with only a single 'Bundle' argument
        @Override
        protected void onPostCreate(Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);
            // Sync the toggle state after onRestoreInstanceState has occurred.
            drawerToggle.syncState();
        }

        private void setupDrawerContent(NavigationView navigationView) {
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            selectDrawerItem(menuItem);
                            return true;
                        }
                    });
        }

        public void selectDrawerItem(MenuItem menuItem) {

            // Create a new fragment and specify the fragment to show based on nav item clicked
            Fragment fragment = null;
            Class fragmentClass;

            switch(menuItem.getItemId()) {
               case R.id.android_i:
                    fragmentClass = Android.class;
                    break;
                case R.id.arduino_i:
                    fragmentClass = Arduino.class;
                    break;
                /*case R.id.lego_i:
                    fragmentClass = LEGO.class;
                    break;
                case R.id.stl_i:
                    fragmentClass = STL.class;
                    break;
                case R.id.navigation_item_five:
                    fragmentClass = FiveFragment.class;
                    break;
                case R.id.navigation_item_six:
                    fragmentClass = SixFragment.class;
                    break;*/
                default:
                    fragmentClass = Android.class;
            }

            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

            // Close the navigation drawer
            mDrawer.closeDrawers();
        }

        private ActionBarDrawerToggle setupDrawerToggle() {
            return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            // Pass any configuration change to the drawer toggles
            drawerToggle.onConfigurationChanged(newConfig);
        }

        private void addFragment(Fragment fragment) {
            FragmentTransaction  transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.flContent, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

        private void replaceFragment(Fragment fragment) {
            FragmentTransaction  transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.flContent, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
}