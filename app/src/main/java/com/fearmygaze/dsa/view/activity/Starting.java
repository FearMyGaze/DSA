package com.fearmygaze.dsa.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.view.fragment.SignIn;
import com.fearmygaze.dsa.view.fragment.SignUp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Starting extends AppCompatActivity {

    public Fragment signIn, signUp;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        user = FirebaseAuth.getInstance().getCurrentUser();


        signIn = new SignIn();
        signUp = new SignUp();

        if (user != null){
            startActivity(new Intent(this,Main.class));
            finish();
        }else {
            replaceFragment(signIn);
        }
    }


    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.startingFrameFragment, fragment);
        fragmentTransaction.commit();
    }
}