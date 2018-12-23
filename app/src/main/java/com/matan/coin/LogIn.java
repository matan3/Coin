package com.matan.coin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        /*
        Button to go to Board
         */
        final Button guestActivity = (Button)findViewById(R.id.logSaveBtn);
        guestActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guestIntent = new Intent (LogIn.this,Board.class);
                startActivity(guestIntent);
            }
        });
    }
}
