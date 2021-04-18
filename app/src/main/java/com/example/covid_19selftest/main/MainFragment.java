package com.example.covid_19selftest.main;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.covid_19selftest.R;
import com.example.covid_19selftest.statistics.Covid_19_stat;
import com.example.covid_19selftest.test.TestActivity;
import com.example.covid_19selftest.test.TestResults;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.ramotion.circlemenu.CircleMenuView;

public class MainFragment extends Fragment {
    private AdView cAdView;

    public static MainFragment newInstance() {

        return new MainFragment();
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
            public void onAdLoaded() { cAdView.setVisibility(View.VISIBLE);
            }
        });

//        final TextView cViewStatistics = root.findViewById(R.id.currentStatBtn);
//        final TextView cPastRecord = root.findViewById(R.id.pastRecordBtn);
//        final TextView cTakeTest = root.findViewById(R.id.take_test);
//        final TextView cCovidGuideline = root.findViewById(R.id.view_safety_tips);

//        cViewStatistics.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fadeButton(cViewStatistics);
//                viewNcdcStats();
//            }
//        });
//
//        cPastRecord.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fadeButton(cPastRecord);
//                launchTestRecords();
//            }
//        });
//
//        cTakeTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fadeButton(cTakeTest);
//                launchTest();
//            }
//        });
//
//        cCovidGuideline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fadeButton(cCovidGuideline);
//                launchSafetyGuideline(view);
//            }
//        });

        CircleMenuView cCircleMenuView = root.findViewById(R.id.circle_menu);
        cCircleMenuView.setEventListener(new CircleMenuView.EventListener() {
            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int buttonIndex) {
                super.onButtonClickAnimationEnd(view, buttonIndex);
                getSelectedButton(view, buttonIndex);
            }
        });
        return root;
    }

    private void getSelectedButton(CircleMenuView view, int buttonIndex) {
        if (buttonIndex == 0) {
            launchTest();
        }
        else if (buttonIndex == 1) {
            launchSafetyGuideline(view);
        }
        else if (buttonIndex == 2) {
            launchTestRecords();
        }
        else if (buttonIndex == 3) {
            viewNcdcStats();
        }
    }

    private void fadeButton(View view) {
        Animator fadeAnimator = AnimatorInflater.loadAnimator(getActivity(), R.animator.fade_button);
        fadeAnimator.setTarget(view);
        fadeAnimator.start();
    }

    private void launchSafetyGuideline(View view) {
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