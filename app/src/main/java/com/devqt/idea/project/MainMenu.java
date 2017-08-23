package com.devqt.idea.project;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.devqt.idea.project.drawer.FragmentDrawer;
import com.devqt.idea.project.fragments.AndroidFragment;
import com.devqt.idea.project.fragments.ArduinoFragment;
import com.devqt.idea.project.fragments.LegoFragment;
import com.devqt.idea.project.fragments.MaxFragment;
import com.devqt.idea.project.fragments.STLFragment;
import com.google.firebase.auth.FirebaseAuth;


public class MainMenu  extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainMenu.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_menu);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);



//        displayView(0);
    }
    private void signOut()
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainMenu.this, LogIn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    public void Seet()
    {
        Intent intent = new Intent(MainMenu.this, Settings.class);
        startActivity(intent) ;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_refresh) {
            return true;


        if (id == R.id.exit) {
                    signOut(); finish();

                    return true;}

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new AndroidFragment();
              //  title = getString(R.string.android);
                break;
            case 1:
                fragment = new ArduinoFragment();
               // title = getString(R.string.arduino);
                break;
            case 2:
                fragment = new LegoFragment();
           //     title = getString(R.string.lego);
                break;
            case 3:
                fragment = new STLFragment();
            //    title = getString(R.string.stl);
                break;
            case 4:
                fragment = new MaxFragment();
           //     title = getString(R.string.a3dmax);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();


            getSupportActionBar().setTitle(title);
        }
    }
}