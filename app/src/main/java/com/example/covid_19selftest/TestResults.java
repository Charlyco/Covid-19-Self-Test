package com.example.covid_19selftest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.covid_19selftest.ui.test_results.TestResultsFragment;

public class TestResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_results_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TestResultsFragment.newInstance())
                    .commitNow();
        }
    }
}