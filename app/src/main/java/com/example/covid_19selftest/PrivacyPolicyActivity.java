package com.example.covid_19selftest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class PrivacyPolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

         Toolbar cToolbar = findViewById(R.id.privacy_toolbar);
        setSupportActionBar(cToolbar);

        ActionBar cActionBar = getSupportActionBar();
        if (cActionBar != null) {
            cActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}