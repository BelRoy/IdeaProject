package com.devqt.idea.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import start.LogIn;
import start.Registration;

public class StartDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_display);

        findViewById(R.id.log_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartDisplay.this, LogIn.class));
                finish();
            }
        });

        findViewById(R.id.regis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartDisplay.this, Registration.class));
                finish();
            }
        });
        findViewById(R.id.idea).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartDisplay.this, NavigatorMenu.class));
            }
        });
    }


}
