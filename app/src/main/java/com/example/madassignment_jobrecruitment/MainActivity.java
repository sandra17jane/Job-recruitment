package com.example.madassignment_jobrecruitment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnlogin, btnsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin = findViewById(R.id.btnlogin);
        btnsignin = findViewById(R.id.btnsignin);

        btnlogin.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
        btnsignin.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SignupActivity.class)));
    }
}
