package com.devqt.idea.project.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.devqt.idea.project.LogIn;
import com.devqt.idea.project.R;
import com.devqt.idea.project.adapter.ItemsModel;
import com.devqt.idea.project.etc.AboutMe;
import com.devqt.idea.project.etc.Settings;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class mBotFragment extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mBlogList;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_bot_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBlogList = (RecyclerView)findViewById(R.id.items_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("mbot");


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
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<ItemsModel, mBotFragment.BlogViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ItemsModel, mBotFragment.BlogViewHolder>(
                        ItemsModel.class,
                        R.layout.elements_row,
                        mBotFragment.BlogViewHolder.class,
                        myRef)  {



                    @Override
                    protected void populateViewHolder(mBotFragment.BlogViewHolder viewHolder, ItemsModel model, int position) {
                        viewHolder.setTitle(model.getName());
                        viewHolder.setImage(getApplicationContext(), model.getIcon());
                    }
                };
        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder  {
        View mView;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView= itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://idea-projects-380e3.firebaseio.com/"));
                    Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");
                    v.getContext().startActivity(browserChooserIntent);
                }
            });

        }
        public void setTitle(String title){
            TextView post_title = (TextView)mView.findViewById(R.id.nameTxt);
            post_title.setText(title);
        }
        public void setImage(Context ctx , String image){
            ImageView post_image = (ImageView)mView.findViewById(R.id.img);

            Picasso.with(ctx).load(image).into(post_image);
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
        Intent intent = new Intent(mBotFragment.this, LogIn.class);
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
                Intent h = new Intent(mBotFragment.this, AndroidFragment.class);
                startActivity(h);
                finish();
                break;
            case R.id.arduino:
                Intent i = new Intent(mBotFragment.this, ArduinoFragment.class);
                startActivity(i);
                finish();
                break;
            case R.id.lego:
                Intent g = new Intent(mBotFragment.this, LegoFragment.class);
                startActivity(g);
                finish();
                break;
            case R.id.stl:
                Intent s = new Intent(mBotFragment.this, STLFragment.class);
                startActivity(s);
                finish();
                break;
            case R.id.max:
                Intent t = new Intent(mBotFragment.this, MaxFragment.class);
                startActivity(t);
                finish();
                break;
            case R.id.mbot:
                Intent b = new Intent(mBotFragment.this, mBotFragment.class);
                startActivity(b);
                finish();
                break;
            case R.id.about_me:
                Intent m = new Intent(mBotFragment.this, AboutMe.class);
                startActivity(m);
                finish();
                break;
            case R.id.sett:
                Intent st = new Intent(mBotFragment.this, Settings.class);
                startActivity(st);
                finish();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
