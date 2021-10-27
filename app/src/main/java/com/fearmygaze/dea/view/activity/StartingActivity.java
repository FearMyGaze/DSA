package com.fearmygaze.dea.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.view.fragment.Sign_in;
import com.fearmygaze.dea.view.fragment.Sign_up;

import java.util.Objects;

public class StartingActivity extends AppCompatActivity {

    public Fragment logInFragment, registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        /*
        * This is to hide the actionbar because i want to add as little as possible themes at the beginning
        * */
        Objects.requireNonNull(getSupportActionBar()).hide();

        logInFragment = new Sign_in();
        registerFragment = new Sign_up();

        replaceFragment(logInFragment);
    }



    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameFragment,fragment);
        fragmentTransaction.commit();
    }
}