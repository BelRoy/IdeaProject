package com.devqt.idea.project;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.devqt.idea.project.drawer.NavigationDrawerHelper;
import com.devqt.idea.project.fragments.AndroidFragment;
import com.devqt.idea.project.fragments.ArduinoFragment;
import com.devqt.idea.project.fragments.LegoFragment;
import com.devqt.idea.project.fragments.MaxFragment;
import com.devqt.idea.project.fragments.STLFragment;
import com.google.firebase.auth.FirebaseAuth;


public class MainMenu extends Activity implements ListView.OnItemClickListener {

    private NavigationDrawerHelper mNavigationDrawerHelper;

    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);



        mNavigationDrawerHelper = new NavigationDrawerHelper();
      mNavigationDrawerHelper.init(this, this);
        mFragment = new AndroidFragment();


        attachFragment();


    }

    private void signOut()
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainMenu.this, LogIn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        /*if (id == R.id.exit) {
            signOut(); finish();
        }*/


        mNavigationDrawerHelper.handleOnOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        mNavigationDrawerHelper.handleSelect(position);


        switch (position) {
            case 0:
                mFragment = new AndroidFragment();
                break;
            case 1:
                mFragment = new ArduinoFragment();
                break;
            case 2:
                mFragment = new LegoFragment();
                break;
            case 3:
                mFragment = new STLFragment();
                break;
            case 4:
                mFragment = new MaxFragment();
                break;
        }
        attachFragment();

    }


    private void attachFragment() {
        if (mFragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, mFragment).commit();

        } else {
            Log.e("MainMenu", "Error in creating fragment");
        }
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mNavigationDrawerHelper.syncState();
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mNavigationDrawerHelper.handleOnPrepareOptionMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        mNavigationDrawerHelper.syncState();
        super.onConfigurationChanged(newConfig);
    }


}