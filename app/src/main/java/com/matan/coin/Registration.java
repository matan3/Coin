package com.matan.coin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        /*
        Button to go to LogIn
         */
        final Button logInActivity = (Button)findViewById(R.id.registrationSaveBtn);
        logInActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrationSaveIntent = new Intent (Registration.this,LogIn.class);
                startActivity(registrationSaveIntent);
            }
        });

    }
}
