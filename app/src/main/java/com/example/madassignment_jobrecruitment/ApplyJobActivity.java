package com.example.madassignment_jobrecruitment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ApplyJobActivity extends AppCompatActivity {
    private TextView tvJobTitle;
    private EditText etName, etEmail;
    private Button btnApply;
    private ImageButton btnBack;
    private String jobTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_job);

        tvJobTitle = findViewById(R.id.tvJobTitle);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        btnApply = findViewById(R.id.btnApply);
        btnBack=findViewById(R.id.btnBack);

        // Get job details from intent
        jobTitle = getIntent().getStringExtra("jobTitle");

        // Set job title (handle null)
        if (jobTitle != null) {
            tvJobTitle.setText("Apply for " + jobTitle);
        } else {
            tvJobTitle.setText("Apply for Job");
        }

        btnApply.setOnClickListener(v -> applyForJob());
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Simulates the back button press
            }
        });

    }

    private void applyForJob() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show success message
        Toast.makeText(this, "Successfully applied for " + jobTitle, Toast.LENGTH_LONG).show();

        // Redirect to UserDashboardActivity
        Intent intent = new Intent(ApplyJobActivity.this, UserDashboardActivity.class);
        intent.putExtra("successMessage", "Successfully applied for " + jobTitle);
        startActivity(intent);
        finish();
    }
}
