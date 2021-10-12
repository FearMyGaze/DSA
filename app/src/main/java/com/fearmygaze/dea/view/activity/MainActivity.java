package com.fearmygaze.dea.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.view.fragment.Sign_in;
import com.fearmygaze.dea.view.fragment.Sign_up;

public class MainActivity extends AppCompatActivity {

    public Fragment sign_in_fragment , sign_up_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign_in_fragment = new Sign_in();
        sign_up_fragment = new Sign_up();

        replaceFragment(sign_in_fragment);
    }



    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_fragment,fragment);
        fragmentTransaction.commit();
    }
}