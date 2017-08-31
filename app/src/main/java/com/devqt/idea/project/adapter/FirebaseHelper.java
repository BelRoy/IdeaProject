package com.devqt.idea.project.adapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class FirebaseHelper {


    DatabaseReference db;
Boolean saved=null;
        ArrayList<String> spacecrafts=new ArrayList<>();


public FirebaseHelper(DatabaseReference db) {
        this.db = db;
        }


public Boolean save(Items items)
        {
        if(items==null)
        {
        saved=false;
        }else
        {
        try
        {
        db.child("Android").push().setValue(items);
        saved=true;
        }catch (DatabaseException e)
        {
        e.printStackTrace();
        saved=false;
        }
        }
        return saved;
        }
//READ
public ArrayList<String> retrieve()
        {
        db.addChildEventListener(new ChildEventListener() {
@Override
public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        fetchData(dataSnapshot);
        }
@Override
public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        fetchData(dataSnapshot);
        }
@Override
public void onChildRemoved(DataSnapshot dataSnapshot) {
        }
@Override
public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        }
@Override
public void onCancelled(DatabaseError databaseError) {
        }
        });
        return spacecrafts;
        }
private void fetchData(DataSnapshot dataSnapshot)
        {
        spacecrafts.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
        String name=ds.getValue(Items.class).getName();
        spacecrafts.add(name);
        }
        }
        }