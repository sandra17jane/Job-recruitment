package com.example.madassignment_jobrecruitment;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserDashboardActivity extends AppCompatActivity {
    private TextView tvUserName, tvUserEmail;
    private Button btnSettings, btnLogout, btnViewJobs;
    private ImageButton btnBack;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        // Initialize UI components
        tvUserName = findViewById(R.id.tvUserName);
        tvUserEmail = findViewById(R.id.tvUserEmail);
        btnSettings = findViewById(R.id.btnSettings);
        btnLogout = findViewById(R.id.btnLogout);
        btnViewJobs = findViewById(R.id.btnViewJobs);
        btnBack=findViewById(R.id.btnBack);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        // Back Button Functionality
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Simulates the back button press
            }
        });


        // Display user details if logged in
        if (user != null) {
            tvUserName.setText(user.getDisplayName() != null ? user.getDisplayName() : "User");
            tvUserEmail.setText(user.getEmail());
        } else {
            tvUserName.setText("Guest");
            tvUserEmail.setText("Not logged in");
        }

        // Button Click Listeners
        btnViewJobs.setOnClickListener(v -> startActivity(new Intent(UserDashboardActivity.this, JobListActivity.class)));

        btnSettings.setOnClickListener(v -> startActivity(new Intent(UserDashboardActivity.this, SettingsActivity.class)));

        btnLogout.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(UserDashboardActivity.this, LoginActivity.class));
            finish();
        });

    }
}
