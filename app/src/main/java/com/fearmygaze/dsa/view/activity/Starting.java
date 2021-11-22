package com.fearmygaze.dsa.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.view.fragment.SignIn;
import com.fearmygaze.dsa.view.fragment.SignUp;

public class Starting extends AppCompatActivity {

    public Fragment logInFragment, registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);


        logInFragment = new SignIn();
        registerFragment = new SignUp();

        replaceFragment(logInFragment);
    }


    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameFragment, fragment);
        fragmentTransaction.commit();
    }
}