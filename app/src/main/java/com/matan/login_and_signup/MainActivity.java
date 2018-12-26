package com.matan.login_and_signup;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.matan.coin.Board;
import com.matan.coin.PrivateZone;
import com.matan.coin.R;

import java.util.ArrayList;

import io.paperdb.Paper;
import io.reactivex.annotations.NonNull;

public class MainActivity extends AppCompatActivity {
    public static int Advertisement_key = 0;
    public static ArrayList<String> a=new ArrayList<String>();
    private static FragmentManager fragmentManager;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            read();
        fragmentManager = getSupportFragmentManager();

        Paper.init(this);

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frameContainer, new Login(),
                            Utils.Login).commit();
        }

        // On close icon click finish activity
        findViewById(R.id.close_activity).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        finish();

                    }
                });
        String userEmailKey = Paper.book().read(Utils.UserEmailKey);
        String userPasswordKey = Paper.book().read(Utils.UserPasswordKey);

        //read email and password that be saved in firebase library
        if(userEmailKey != ""&&userPasswordKey!=""){
            if(!TextUtils.isEmpty(userEmailKey)&&!TextUtils.isEmpty(userPasswordKey)){
                autoLogIn(userEmailKey,userPasswordKey);
            }
        }

    }

    private void autoLogIn(String mail, String password) {

        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Intent intent=new Intent(MainActivity.this,Board.class);
                startActivity(intent);

            }
        });
    }

    // Replace Login Fragment with animation
    protected void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new Login(),
                        Utils.Login).commit();
    }

    @Override
    public void onBackPressed() {

        // Find the tag of signup and forgot password fragment
        Fragment SignUp_Fragment = fragmentManager
                .findFragmentByTag(Utils.SignUp);
        Fragment ForgotPassword_Fragment = fragmentManager
                .findFragmentByTag(Utils.ForgotPassword);

        // Check if both are null or not
        // If both are not null then replace login fragment else do backpressed
        // task

        if (SignUp_Fragment != null)
            replaceLoginFragment();
        else if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }

    private FirebaseDatabase mDatabase;
    private DatabaseReference dbRef;
    private  void read(){
        Toast.makeText(getApplicationContext(),
                "read" , Toast.LENGTH_LONG)
                .show();
        mDatabase = FirebaseDatabase.getInstance();
        dbRef = mDatabase.getReference("/user");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot datas : snapshot.getChildren()) {
                    MainActivity.a.add(datas.toString());


                }
                // MainActivity.a.add((User)snapshot.getValue().);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
    }
}
