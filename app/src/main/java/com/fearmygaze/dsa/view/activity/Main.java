package com.fearmygaze.dsa.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.User;
import com.fearmygaze.dsa.view.fragment.Examinations;
import com.fearmygaze.dsa.view.fragment.Notifications;
import com.fearmygaze.dsa.view.fragment.ProfileFrag;
import com.fearmygaze.dsa.view.fragment.Upload;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public Fragment files, profile, upload;
    
    public Fragment notifications;

    DrawerLayout drawerLayout;

    MaterialToolbar mainMaterialToolbar;
    BottomNavigationView mainBottomNavigationView;
    FloatingActionButton fab;

    NavigationView mainNavView;

    FirebaseAuth fireBaseAuth;
    FirebaseUser user;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fireBaseAuth = FirebaseAuth.getInstance(); // Firebase authentication
        user = fireBaseAuth.getCurrentUser(); //Firebase user

        drawerLayout = findViewById(R.id.mainDrawerLayout);

        fab = findViewById(R.id.mainFab);
        mainMaterialToolbar = findViewById(R.id.mainMaterialToolbar);
        mainBottomNavigationView = findViewById(R.id.mainBottomNavigation);

        mainNavView = findViewById(R.id.mainNavView);


//        SharedPreferences getSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


//        String userName = getSharedPrefs.getString("userName","empty");
//        String userEmail = getSharedPrefs.getString("userEmail","empty");
//        int userId = getSharedPrefs.getInt("userID",-1);
//
//        User me = new User(userName,userEmail,userId);
//
//        profile = new ProfileFrag(me);
        files = new Examinations();
        notifications = new Notifications();
        upload = new Upload();

        setSupportActionBar(mainMaterialToolbar); //for drawer
        mainBottomNavigationView.setBackground(null); //This will fix the background color error

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,mainMaterialToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mainNavView.setNavigationItemSelectedListener(this);
        
        
        
        mainBottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottomNavHome:
                    replaceFragment(files);
                    return true;
                case R.id.bottomNavMessage:
                    Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.bottomNavNotification:
                    replaceFragment(notifications);
                    return true;
                case R.id.bottomNavSearch:
                    Toast.makeText(this, "Not yet implemented", Toast.LENGTH_LONG).show();
                    return true;
                default:
                    return false;
            }
        });

    replaceFragment(files);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.drawerItemProfile:
                startActivity(new Intent(this,Profile.class));
                return true;
            case R.id.drawerItemSettings:
                startActivity(new Intent(this,Settings.class));
                return true;
            case R.id.drawerItemSignOut:
                FirebaseAuth.getInstance().signOut();
                finish();
                return true;
            default:
                return false;
        }
    }
}