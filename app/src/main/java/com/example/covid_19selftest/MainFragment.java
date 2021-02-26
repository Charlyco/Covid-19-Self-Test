package com.example.covid_19selftest;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class MainFragment extends Fragment {
    private AdView cAdView;


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root  = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);

        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        cAdView = root.findViewById(R.id.adViewMain);
        AdRequest cAdRequest = new AdRequest.Builder().build();
        cAdView.loadAd(cAdRequest);
        cAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                cAdView.setVisibility(View.VISIBLE);
            }
        });

        TextView cViewStatistics = root.findViewById(R.id.currentStatBtn);
        TextView cPastRecord = root.findViewById(R.id.pastRecordBtn);
        TextView cTakeTest = root.findViewById(R.id.take_test);
        TextView cCovidGuideline = root.findViewById(R.id.view_safety_tips);

        cViewStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewNcdcStats();
            }
        });

        cPastRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTestRecords();
            }
        });

        cTakeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTest();
            }
        });

        cCovidGuideline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSafetyGuideline(view);
            }
        });

        return root;
    }

    private void launchSafetyGuideline( View view) {
        NavDirections action = MainFragmentDirections
                .actionMainFragmentToSafetyGuidelines();
        Navigation.findNavController(view).navigate(action);
    }

    public void launchTest() {
        startActivity(new Intent(getActivity(), TestActivity.class));
    }

    public void launchTestRecords() {
        startActivity(new Intent(getActivity(), TestResults.class));
    }

    public void viewNcdcStats() {
        startActivity(new Intent(getActivity(), Covid_19_stat.class));
    }
}