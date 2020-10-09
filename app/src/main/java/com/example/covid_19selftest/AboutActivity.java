package com.example.covid_19selftest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar cToolbar = findViewById(R.id.about_toolbar);
        setSupportActionBar(cToolbar);

        ActionBar cActionBar = getSupportActionBar();
        if (cActionBar != null) {
            cActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}