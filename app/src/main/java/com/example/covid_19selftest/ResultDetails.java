package com.example.covid_19selftest;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.covid_19selftest.data.Result;
import com.example.covid_19selftest.ui.test_results.ResultViewModel;

public class ResultDetails extends Fragment {
    private ResultViewModel cResultViewModel;
    private TextView cUserName, cSymptom1, cSymptom2, cSymptom3, cSymptom4,
            cSymptom5, cSymptom6, cHealthCondition, cStatus;

       public static ResultDetails newInstance() {
        return new ResultDetails();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
           View root = inflater.inflate(R.layout.result_details_fragment, container, false);
           cUserName = root.findViewById(R.id.hello_user);
           cSymptom1 = root.findViewById(R.id.symptom1);
           cSymptom2 = root.findViewById(R.id.symptom2);
           cSymptom3 = root.findViewById(R.id.symptom3);
           cSymptom4 = root.findViewById(R.id.symptom4);
           cSymptom5 = root.findViewById(R.id.symptom5);
           cSymptom6 = root.findViewById(R.id.symptom6);
           cHealthCondition = root.findViewById(R.id.healthComplication);
           cStatus = root.findViewById(R.id.status_info);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cResultViewModel = new ViewModelProvider(requireActivity()).get(ResultViewModel.class);
        cResultViewModel.getSelected().observe(getViewLifecycleOwner(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
               setResultDetails(result);
            }
        });
    }

    private void setResultDetails(Result result) {
           StringBuilder cNameBuilder = new StringBuilder();
           cNameBuilder.append("Hello! ");
           cNameBuilder.append(result.getName());
        cUserName.setText(cNameBuilder);

        if (result.getBreathing() == null) {
            cSymptom1.setText(R.string.no);
            cSymptom1.setTextColor(Color.GREEN);
        }else {
            cSymptom1.setText(R.string.yes);
            cSymptom1.setTextColor(Color.RED);
        }

        if (result.getChestPain() == null) {
            cSymptom2.setText(R.string.no);
            cSymptom2.setTextColor(Color.GREEN);
        } else {
            cSymptom2.setText(R.string.yes);
            cSymptom2.setTextColor(Color.RED);
        }

        if (result.getSpeechLoss() == null) {
            cSymptom3.setText(R.string.no);
            cSymptom3.setTextColor(Color.GREEN);
        } else {
            cSymptom3.setText(R.string.yes);
            cSymptom3.setTextColor(Color.RED);
        }

        if (result.getFever() == null) {
            cSymptom4.setText(R.string.no);
            cSymptom4.setTextColor(Color.GREEN);
        } else {
            cSymptom4.setText(R.string.yes);
            cSymptom4.setTextColor(Color.RED);
        }

        if (result.getDryCough() == null) {
            cSymptom5.setText(R.string.no);
            cSymptom5.setTextColor(Color.GREEN);
        } else {
            cSymptom5.setText(R.string.yes);
            cSymptom5.setTextColor(Color.RED);
        }

        if (result.getTiredness() == null) {
            cSymptom6.setText(R.string.no);
            cSymptom6.setTextColor(Color.GREEN);
        } else {
            cSymptom6.setText(R.string.yes);
            cSymptom6.setTextColor(Color.RED);
        }

        if (result.getHealthCondition() == null){
            cHealthCondition.setText(R.string.no);
            cHealthCondition.setTextColor(Color.GREEN);
        } else {
            StringBuilder cHealthBuilder = new StringBuilder();
            cHealthBuilder.append("You have the following health complication: ");
            cHealthBuilder.append(result.getHealthCondition());
            cHealthCondition.setText(cHealthBuilder);
            cHealthCondition.setTextColor(Color.RED);
        }
        StringBuilder status = new StringBuilder();
        status.append("Your probable Covid-19 status: ");
        status.append(result.getStatus());
        cStatus.setText(status);
        if (result.getStatus().equals("Most Likely Positive")) {
            cStatus.setTextColor(Color.RED);
        } else if (result.getStatus().equals("Likely Positive")) {
            cStatus.setTextColor(Color.YELLOW);
        } else cStatus.setTextColor(Color.GREEN);
    }
}