package com.example.covid_19selftest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SafetyGuidelines extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_safety_guidelines, container, false);
        TextView cWearMast = root.findViewById(R.id.wear_mask);
        TextView cSocialDistancing = root.findViewById(R.id.social_distancing);
        TextView cWashHands = root.findViewById(R.id.wash_hand);
        TextView cCoverCoughs = root.findViewById(R.id.cover_coughs);
        TextView cHygiene = root.findViewById(R.id.clean_and_disinfect);
        TextView cMonitorHealth = root.findViewById(R.id.monitor_health);

        cWearMast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                howToWearMask(view);
            }
        });

        cSocialDistancing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                socialDistancing(view);
            }
        });

        cWashHands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                howToWashHands(view);
            }
        });
        
        cCoverCoughs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                howToCoverCoughs(view);
            }
        });
        
        cHygiene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodHygiene(view);
            }
        });
        
        cMonitorHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                howToMonitorHealth(view);
            }
        });
        
        return root;
    }

    private void howToMonitorHealth(View view) {
        String details = getString(R.string.monitor_health_details);
        showDetails(details, view);
    }

    private void goodHygiene(View view) {
        String details = getString(R.string.good_hygiene_details);
        showDetails(details, view);
    }

    private void howToCoverCoughs(View view) {
        String details = getString(R.string.cover_cough_details);
        showDetails(details, view);
    }

    private void howToWashHands(View view) {
        String details = getString(R.string.wash_hand_details);
        showDetails(details, view);
    }

    private void socialDistancing(View view) {
        String details = getString(R.string.social_distance_details);
        showDetails(details, view);
    }

    private void howToWearMask(View view) {
        String details = getString(R.string.wear_mask_details);
        showDetails(details, view);
    }

    public void showDetails(String text, View view) {
        Bundle cBundle = new Bundle();
        cBundle.putString("details", text);
        Navigation.findNavController(view).navigate(
                R.id.action_safetyGuidelines_to_safetyGuidelineDetailsFragment,
                cBundle);
    }
}