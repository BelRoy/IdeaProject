package com.devqt.idea.project.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.devqt.idea.project.LogIn;
import com.devqt.idea.project.R;
import com.devqt.idea.project.adapter.ItemsAdapter;
import com.devqt.idea.project.adapter.ItemsModel;
import com.devqt.idea.project.etc.AboutMe;
import com.devqt.idea.project.etc.Settings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AndroidFragment extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private List<ItemsModel> result;
    private ItemsAdapter itemsAdapter;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    final int N = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("android");


        result = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.items_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        createResult();

        itemsAdapter = new ItemsAdapter(result);
        recyclerView.setAdapter(itemsAdapter);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);



    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case 0:
                break;

            case 1:
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void  createResult(){


        for (int i = 0; i < N; i++){

            result.add(new ItemsModel("name","description","icon",""));
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
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void signOut()
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(AndroidFragment.this, LogIn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        if (id == R.id.action_refresh) {
            return true;
        }
        if (id == R.id.exit) {
            signOut(); finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {

            case R.id.android:
                Intent h = new Intent(AndroidFragment.this, AndroidFragment.class);
                startActivity(h);
                finish();
                break;
            case R.id.arduino:
                Intent i = new Intent(AndroidFragment.this, ArduinoFragment.class);
                startActivity(i);
                finish();
                break;
            case R.id.lego:
                Intent g = new Intent(AndroidFragment.this, LegoFragment.class);
                startActivity(g);
                finish();
                break;
            case R.id.stl:
                Intent s = new Intent(AndroidFragment.this, STLFragment.class);
                startActivity(s);
                finish();
                break;
            case R.id.max:
                Intent t = new Intent(AndroidFragment.this, MaxFragment.class);
                startActivity(t);
                finish();
                break;
            case R.id.mbot:
                Intent b = new Intent(AndroidFragment.this, mBotFragment.class);
                startActivity(b);
                finish();
                break;
            case R.id.about_me:
                Intent m = new Intent(AndroidFragment.this, AboutMe.class);
                startActivity(m);
                finish();
                break;
            case R.id.sett:
                Intent st = new Intent(AndroidFragment.this, Settings.class);
                startActivity(st);
                finish();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
