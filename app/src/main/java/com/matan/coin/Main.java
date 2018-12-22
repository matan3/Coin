package com.matan.coin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    /*
    Key of each advertisement for FireBase
     */
    public  static  int Advertisement_key=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Button to go to Regitration
         */
        final Button regitrationActivity = (Button)findViewById(R.id.registerBtn);
        regitrationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrationIntent = new Intent (Main.this,Registration.class);
                startActivity(registrationIntent);
            }
        });
        //8888888888888

        /*
        Button to go to LogIn
         */
        final Button logInActivity = (Button)findViewById(R.id.logInBtn);
        logInActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logInIntent = new Intent (Main.this,LogIn.class);
                startActivity(logInIntent);
            }
        });

        /*
        Button to go to Board
         */
        final Button guestActivity = (Button)findViewById(R.id.guestBtn);
        guestActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guestIntent = new Intent (Main.this,Boardnew2.class);
                startActivity(guestIntent);
            }
        });
    }
}
