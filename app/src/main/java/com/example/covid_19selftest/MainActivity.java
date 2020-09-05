package com.example.covid_19selftest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    private AdView cAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar cToolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(cToolbar);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
      cAdView = findViewById(R.id.adViewMain);
        AdRequest cAdRequest = new AdRequest.Builder().build();
        cAdView.loadAd(cAdRequest);
        cAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                cAdView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(this, SettingsActivity.class));
        return super.onOptionsItemSelected(item);
    }

    public void launchTest(View view) {
        startActivity(new Intent(this, TestActivity.class));
    }

    public void launchTestRecords(View view) {
        startActivity(new Intent(this, TestResults.class));
    }

    public void viewNcdcStats(View view) {
        startActivity(new Intent(this, Covid_19_stat.class));
    }
}