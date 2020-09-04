package com.example.covid_19selftest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

public class TestResults extends AppCompatActivity {
    Fragment cResultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_results_activity);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.nav_host_fragment, TestResultsFragment.newInstance())
//                    .commitNow();
        cResultFragment = TestResultsFragment.newInstance();

    }


}