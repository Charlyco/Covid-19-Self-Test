package com.example.covid_19selftest.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.covid_19selftest.R;

public class TestResults extends AppCompatActivity {
    Fragment cResultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_results_activity);

        Toolbar cToolbar = findViewById(R.id.resultToolbar);
        setSupportActionBar(cToolbar);

//        ActionBar cActionBar = getSupportActionBar();
//        if (cActionBar != null) {
//            cActionBar.setDisplayHomeAsUpEnabled(true);
//        }

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.nav_host_fragment, TestResultsFragment.newInstance())
//                    .commitNow();
        cResultFragment = TestResultsFragment.newInstance();

    }


}