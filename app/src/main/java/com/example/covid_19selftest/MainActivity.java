package com.example.covid_19selftest;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
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
    Toolbar cToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        cToolbar = findViewById(R.id.toolbar_main);
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
        MenuInflater cInflater = getMenuInflater();
        cInflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.twitter:
                String userId = "Limitless_OCC";
                PackageManager cPackageManager1 = getPackageManager();
                openTwitter(cPackageManager1, userId);
                return true;
            case R.id.facebook:
                String pageId = "charlycotechnologies";
                PackageManager cPackageManager = getPackageManager();
                openFaceBookIntent(cPackageManager, pageId);
                return true;
            case R.id.linkedIn:
                String myId = "limitlessb4a7b9145";
                PackageManager cPackageManager2 = getPackageManager();
                openLinkedin(cPackageManager2, myId);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openLinkedin(PackageManager packageManager2, String myId) {
        String urlLn="https://www.linkedin.com/" + myId;
        try {
            packageManager2.getPackageInfo("com.linkedin.android", 0);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://" + myId)));
        } catch (PackageManager.NameNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlLn) ));
        }
    }

    private void openTwitter(PackageManager packageManager1, String userId) {
        try {
            packageManager1.getPackageInfo("com.twitter.android", 0);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=" + userId)));
        } catch (PackageManager.NameNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + userId) ));
        }
    }


    private void openFaceBookIntent(PackageManager packageManager, String pageId) {
        String pageUrl = "https://web.facebook.com/" + pageId;
        try {
            ApplicationInfo cApplicationInfo = packageManager.getApplicationInfo("com.facebook.katana", 0);
            if (cApplicationInfo.enabled) {
                long versionCode;
                String url;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                     versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).getLongVersionCode();
                } else {
                     versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
                }
                if (versionCode >= 3002850) {
                    url = "fb://facewebmodal/f?href=" + pageUrl;
                } else {
                    url = "fb://page/" + pageUrl;
                }
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        } catch (PackageManager.NameNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl)));
        }

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