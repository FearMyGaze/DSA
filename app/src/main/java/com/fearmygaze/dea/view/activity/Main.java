package com.fearmygaze.dea.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.view.fragment.Home;
import com.fearmygaze.dea.view.fragment.Notifications;
import com.fearmygaze.dea.view.fragment.PatientFiles;
import com.fearmygaze.dea.view.fragment.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Main extends AppCompatActivity {

    public Fragment home, patientFiles, notifications, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        /*
        * TODO: CHANGE THE BottomNavigationView Like Monsta
        * */

        home = new Home();
        profile = new Profile();
        patientFiles = new PatientFiles();
        notifications = new Notifications();

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
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.itemHome:
                        replaceFragment(home);
                        return true;
                    case R.id.itemNotifications:
                        replaceFragment(notifications);
                        return true;
                    case R.id.itemPatientFiles:
                        replaceFragment(patientFiles);
                        return true;
                    case R.id.itemProfile:
                        replaceFragment(profile);
                        return true;
                    default:
                        return false;
                }
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