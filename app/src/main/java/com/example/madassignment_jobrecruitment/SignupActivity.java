package com.example.madassignment_jobrecruitment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private EditText signupEmail, signupPassword;
    private Button signupButton;
    private TextView loginRedirectText;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize UI components
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Signup Button Click Listener
        signupButton.setOnClickListener(v -> handleSignup());

        // Redirect to Login Activity
        loginRedirectText.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void handleSignup() {
        String email = signupEmail.getText().toString().trim();
        String password = signupPassword.getText().toString().trim();

        if (validateInput(email, password)) {
            signupButton.setEnabled(false); // Prevent multiple clicks
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(authResult -> {
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            user.sendEmailVerification() // ✅ Send verification email
                                    .addOnSuccessListener(aVoid ->
                                            Toast.makeText(SignupActivity.this, "Verification email sent!", Toast.LENGTH_SHORT).show())
                                    .addOnFailureListener(e -> Log.e("FirebaseAuth", "Email Verification Error", e));

                            Toast.makeText(SignupActivity.this, "Sign-up Successful! Please verify your email.", Toast.LENGTH_LONG).show();

                            // Redirect to Login Page after Signup
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(SignupActivity.this, "Signup Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("FirebaseAuth", "Signup Error", e);
                        signupButton.setEnabled(true);
                    });
        }
    }

    private boolean validateInput(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            signupEmail.setError("Email cannot be empty");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signupEmail.setError("Please enter a valid email");
            return false;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            signupPassword.setError("Password must be at least 6 characters");
            return false;
        }
        return true;
    }
}
