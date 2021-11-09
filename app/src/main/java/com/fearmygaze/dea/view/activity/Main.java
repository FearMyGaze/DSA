package com.fearmygaze.dea.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.custom.MyToast.CustomToast;
import com.fearmygaze.dea.view.fragment.Home;
import com.fearmygaze.dea.view.fragment.Notifications;
import com.fearmygaze.dea.view.fragment.PatientFiles;
import com.fearmygaze.dea.view.fragment.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class Main extends AppCompatActivity {

    public Fragment home, patientFiles, notifications, profile;
    public CustomToast customToast;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        home = new Home();
        profile = new Profile();
        patientFiles = new PatientFiles();
        notifications = new Notifications();
        customToast = new CustomToast(Main.this);

        replaceFragment(home);

        /*
        * TODO: This is for badge number but change the language(is kotlin)
        * var badge = bottomNavigation.getOrCreateBadge(menuItemId)
        * badge.isVisible = true
        * An icon only badge will be displayed unless a number is set:
        * badge.number = 99
        * val badgeDrawable = bottomNavigation.getBadge(menuItemId)
        * if (badgeDrawable != null) {
        * badgeDrawable.isVisible = false
        * badgeDrawable.clearNumber()
        *}
        * bottomNavigation.removeBadge(menuItemId)
        * */
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.itemHome:
                    replaceFragment(home);
                    return true;
                case R.id.itemNotifications:
                    replaceFragment(notifications);
                    Objects.requireNonNull(getSupportActionBar()).hide();
                    return true;
                case R.id.itemPatientFiles:
                    replaceFragment(patientFiles);
                    Objects.requireNonNull(getSupportActionBar()).hide();
                    return true;
                case R.id.itemProfile:
                    replaceFragment(profile);
                    Objects.requireNonNull(getSupportActionBar()).hide();
                    return true;
                default:
                    return false;
            }
        });

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame,fragment);
        fragmentTransaction.commit();
    }
}