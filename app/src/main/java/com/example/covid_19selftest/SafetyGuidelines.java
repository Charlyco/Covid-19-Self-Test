package com.example.covid_19selftest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SafetyGuidelines extends Fragment {

    public SafetyGuidelines() {
        // Required empty public constructor
    }

        public static SafetyGuidelines newInstance(String param1, String param2) {
        SafetyGuidelines fragment = new SafetyGuidelines();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_safety_guidelines, container, false);
    }
}