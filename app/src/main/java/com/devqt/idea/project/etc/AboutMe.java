package com.devqt.idea.project.etc;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.devqt.idea.project.LogIn;
import com.devqt.idea.project.R;
import com.devqt.idea.project.fragments.AndroidFragment;
import com.devqt.idea.project.fragments.ArduinoFragment;
import com.devqt.idea.project.fragments.LegoFragment;
import com.devqt.idea.project.fragments.MaxFragment;
import com.devqt.idea.project.fragments.STLFragment;
import com.devqt.idea.project.fragments.mBotFragment;
import com.google.firebase.auth.FirebaseAuth;

public class AboutMe extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        TextView textView = (TextView) findViewById(R.id.me);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/megadeth.ttf");
        textView.setTypeface(typeFace);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"develop.qt@gmail.com"});
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        "Add your Subject");
                Email.putExtra(Intent.EXTRA_TEXT, "Dear Developer Name," + "");
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
            }
        });
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
        Intent intent = new Intent(AboutMe.this, LogIn.class);
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
                Intent h = new Intent(AboutMe.this, AndroidFragment.class);
                startActivity(h);
                finish();
                break;
            case R.id.arduino:
                Intent i = new Intent(AboutMe.this, ArduinoFragment.class);
                startActivity(i);
                finish();
                break;
            case R.id.lego:
                Intent g = new Intent(AboutMe.this, LegoFragment.class);
                startActivity(g);
                finish();
                break;
            case R.id.stl:
                Intent s = new Intent(AboutMe.this, STLFragment.class);
                startActivity(s);
                finish();
                break;
            case R.id.mbot:
                Intent b = new Intent(AboutMe.this, mBotFragment.class);
                startActivity(b);
                finish();
                break;
            case R.id.max:
                Intent t = new Intent(AboutMe.this, MaxFragment.class);
                startActivity(t);
                finish();
                break;
            case R.id.about_me:
                Intent m = new Intent(AboutMe.this, AboutMe.class);
                startActivity(m);
                finish();
                break;
            case R.id.sett:
                Intent st = new Intent(AboutMe.this, Settings.class);
                startActivity(st);
                finish();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
