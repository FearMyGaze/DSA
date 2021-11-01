package com.fearmygaze.dea.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.custom.MyToast.CustomToast;
import com.fearmygaze.dea.view.fragment.SignIn;
import com.fearmygaze.dea.view.fragment.SignUp;

import java.util.Objects;

public class Starting extends AppCompatActivity {

    public Fragment logInFragment, registerFragment;
    public CustomToast customToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        /*
        * This is to hide the actionbar because i want to add as little as possible themes at the beginning
        * */
        Objects.requireNonNull(getSupportActionBar()).hide();


        customToast =new CustomToast(Starting.this);
        logInFragment = new SignIn();
        registerFragment = new SignUp();

        replaceFragment(logInFragment);
    }



    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameFragment,fragment);
        fragmentTransaction.commit();
    }
}