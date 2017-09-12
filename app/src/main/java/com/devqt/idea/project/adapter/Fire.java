package com.devqt.idea.project.adapter;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;


public class Fire extends Application {


    @Override
    public void onCreate(){
        super.onCreate();

        /*Firebase.setAndroidContext(this);*/

        if (!FirebaseApp.getApps(this).isEmpty()){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }

}
