package com.fearmygaze.dsa.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.User;
import com.fearmygaze.dsa.view.fragment.Files;
import com.fearmygaze.dsa.view.fragment.Notifications;
import com.fearmygaze.dsa.view.fragment.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Main extends AppCompatActivity {

    public Fragment files, notifications, profile;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences getSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        FloatingActionButton mainFilesAdd = findViewById(R.id.mainFilesAdd);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        String userName = getSharedPrefs.getString("userName","empty");
        String userEmail = getSharedPrefs.getString("userEmail","empty");
        int userId = getSharedPrefs.getInt("userID",-1);

        User me = new User(userName,userEmail,userId);

        profile = new Profile(me);
        files = new Files(me);
        notifications = new Notifications(me);

        mainFilesAdd.setOnClickListener(v -> {
            Intent intent = new Intent(Main.this, FileUpload.class);
            startActivity(intent);
            finish();
        });


        replaceFragment(files);

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
        bottomNavigationView.setSelectedItemId(R.id.itemFiles);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.itemNotifications:
                    replaceFragment(notifications);
                    return true;
                case R.id.itemFiles:
                    replaceFragment(files);
                    return true;
                case R.id.itemProfile:
                    replaceFragment(profile);
                    return true;
                default:
                    return false;
            }
        });

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.commit();
    }
}