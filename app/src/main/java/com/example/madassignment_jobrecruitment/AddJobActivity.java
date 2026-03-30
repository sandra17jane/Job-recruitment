package com.example.madassignment_jobrecruitment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class AddJobActivity extends AppCompatActivity {

    EditText etTitle, etCompany, etSalary, etDescription;
    Button btnSubmit;
    ImageButton btnBack;
    FirebaseFirestore db; // Firestore instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        // Initialize Views
        etTitle = findViewById(R.id.etTitle);
        etCompany = findViewById(R.id.etCompany);
        etSalary = findViewById(R.id.etSalary);
        etDescription = findViewById(R.id.etDescription);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnBack = findViewById(R.id.btnBack);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Back Button Functionality
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Simulates the back button press
            }
        });



        // Submit Button Click
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addJobToFirestore();
            }
        });
    }
    private void addJobToFirestore() {
        String title = etTitle.getText().toString().trim();
        String company = etCompany.getText().toString().trim();
        String salary = etSalary.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        // Validation to ensure fields are not empty
        if (title.isEmpty() || company.isEmpty() || salary.isEmpty() || description.isEmpty()) {
            Toast.makeText(AddJobActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a job object
        Map<String, Object> job = new HashMap<>();
        job.put("title", title);
        job.put("company", company);
        job.put("salary", salary);
        job.put("description", description);

        // Add job to Firestore collection
        db.collection("Jobs")
                .add(job)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(AddJobActivity.this, "Job Added Successfully!", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity after adding the job
                })
                .addOnFailureListener(e -> Toast.makeText(AddJobActivity.this, "Error adding job: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}