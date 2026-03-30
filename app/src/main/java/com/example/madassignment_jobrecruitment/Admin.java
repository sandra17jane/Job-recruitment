package com.example.madassignment_jobrecruitment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class Admin extends AppCompatActivity {

    private static final String TAG = "Admin";
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    Button manageJobsBtn, viewJobsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Initialize Views
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        manageJobsBtn = findViewById(R.id.manageJobsBtn);
        viewJobsBtn = findViewById(R.id.ViewJobsBtn);

        // Set up Toolbar
        setSupportActionBar(toolbar);

        // Enable Hamburger Icon
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Handle Navigation Menu Clicks
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                Log.d(TAG, "Navigation item selected: " + itemId);
                Toast.makeText(Admin.this, "Selected item: " + itemId, Toast.LENGTH_SHORT).show(); // For debugging

                if (itemId == R.id.nav_add_job) {
                    startActivity(new Intent(Admin.this, AddJobActivity.class));
                } else if (itemId == R.id.nav_joblist) {  // Ensure this matches the XML ID
                    Log.d(TAG, "Navigating to JobListActivity");
                    startActivity(new Intent(Admin.this, JobListActivity.class));
                } else if (itemId == R.id.nav_logout) {
                    finish();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // Handle Button Clicks
        manageJobsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this, AddJobActivity.class));
            }
        });

        viewJobsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this, JobListActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
