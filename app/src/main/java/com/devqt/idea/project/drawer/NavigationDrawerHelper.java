package com.devqt.idea.project.drawer;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.devqt.idea.project.R;

public class NavigationDrawerHelper {

    DrawerLayout mDrawerLayout;
    ListView mDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;



    public void init(Activity activity, ListView.OnItemClickListener listener) {


        mDrawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        mDrawerListView = (ListView) activity.findViewById(R.id.left_drawer);


        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[4];

        drawerItem[0] = new ObjectDrawerItem(R.drawable.android_ico, "Android");
        drawerItem[1] = new ObjectDrawerItem(R.drawable.arduino_ico, "Arduino");
        drawerItem[2] = new ObjectDrawerItem(R.drawable.lego_ico, "LEGO");
        drawerItem[3] = new ObjectDrawerItem(R.drawable.ic_stl, "STL");
        drawerItem[4] = new ObjectDrawerItem(R.drawable.ic_max, "3D Max");



        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(activity, R.layout.listview_drawer_item_row, drawerItem);


        mDrawerListView.setAdapter(adapter);
        mDrawerListView.setOnItemClickListener(listener);


        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerListView.setItemChecked(0,true);


        setupActionBar(activity);
    }


    private void setupActionBar(final Activity theActivity) {
        final Activity activity = theActivity;

        ActionBar actionBar = theActivity.getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        mDrawerToggle = new ActionBarDrawerToggle(
                theActivity,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.DrawerOpen,
                R.string.DrawerClosed
        ){
            @Override
            public void onDrawerOpened(View drawerView) {
                activity.invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                activity.invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }
        };

    }


    public void handleSelect(int option) {
        mDrawerListView.setItemChecked(option,true);
        mDrawerLayout.closeDrawer(mDrawerListView);

    }


    public void handleOnPrepareOptionMenu(Menu menu) {
        boolean itemVisible = !mDrawerLayout.isDrawerOpen(mDrawerListView);
        for (int index = 0; index < menu.size(); index++) {
            MenuItem item = menu.getItem(index);
            item.setEnabled(itemVisible);
        }

    }


    public void handleOnOptionsItemSelected(MenuItem menuItem) {
        mDrawerToggle.onOptionsItemSelected(menuItem);

    }


    public void syncState() {
        mDrawerToggle.syncState();
    }

    public void setSelection(int option) {
        mDrawerListView.setItemChecked(option,true);
    }
}