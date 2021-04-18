package com.example.covid_19selftest.safety_guide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.covid_19selftest.R;


public class SafetyGuidelineDetailsFragment extends Fragment {

    public SafetyGuidelineDetailsFragment() {
        // Required empty public constructor
    }

    public static SafetyGuidelineDetailsFragment newInstance() {
        SafetyGuidelineDetailsFragment fragment = new SafetyGuidelineDetailsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_safety_guideline_details, container, false);
        TextView cTextView = root.findViewById(R.id.safety_guide_details);
        cTextView.setText(getArguments().getString("details"));
        return root;
    }
}