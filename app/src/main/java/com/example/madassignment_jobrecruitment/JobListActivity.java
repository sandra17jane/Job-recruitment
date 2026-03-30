package com.example.madassignment_jobrecruitment;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class JobListActivity extends AppCompatActivity {
    private LinearLayout linearLayoutJobs;
    private FirebaseFirestore db;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        // Initialize Views
        linearLayoutJobs = findViewById(R.id.linearLayoutJobs);
        btnBack = findViewById(R.id.btnBack);
        db = FirebaseFirestore.getInstance(); // Initialize Firestore

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Simulates the back button press
            }
        });


        // Load Jobs from Firestore
        loadJobs();
    }

    private void loadJobs() {
        db.collection("Jobs")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        if (e != null) {
                            Toast.makeText(JobListActivity.this, "Error loading jobs: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Clear existing job views
                        linearLayoutJobs.removeAllViews();

                        // Iterate through job documents
                        for (DocumentSnapshot doc : documentSnapshots) {
                            String title = doc.getString("title");
                            String company = doc.getString("company");
                            String salary = doc.getString("salary");
                            String description = doc.getString("description");

                            // Inflate job view
                            View jobView = LayoutInflater.from(JobListActivity.this).inflate(R.layout.job_item, linearLayoutJobs, false);

                            // Populate job view
                            TextView tvTitle = jobView.findViewById(R.id.jobTitle);
                            TextView tvCompany = jobView.findViewById(R.id.jobCompany);
                            TextView tvSalary = jobView.findViewById(R.id.jobSalary);
                            TextView tvDescription = jobView.findViewById(R.id.jobDescription);
                            Button applyButton = jobView.findViewById(R.id.applyButton);

                            tvTitle.setText(title);
                            tvCompany.setText(company);
                            tvSalary.setText(salary);
                            tvDescription.setText(description);

                            // Apply Job Button Click
                            applyButton.setOnClickListener(v -> {
                                Intent intent = new Intent(JobListActivity.this, ApplyJobActivity.class);
                                intent.putExtra("jobTitle", title);
                                intent.putExtra("jobCompany", company);
                                startActivity(intent);
                            });

                            // Add job view to the layout
                            linearLayoutJobs.addView(jobView);
                        }
                    }
                });
    }
}
