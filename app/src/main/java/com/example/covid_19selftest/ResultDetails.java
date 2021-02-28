package com.example.covid_19selftest;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.covid_19selftest.data.Result;
import com.example.covid_19selftest.ui.test_results.ResultViewModel;

public class ResultDetails extends Fragment {
    private TextView cUserName, cSymptom1, cSymptom2, cSymptom3, cSymptom4,
            cSymptom5, cSymptom6, cSymptom7, cSymptom8, cHealthCondition, cRecommendation;
    private Button cForward;
    private Result cResult;

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
           cSymptom7 = root.findViewById(R.id.symptom7);
           cSymptom8 = root.findViewById(R.id.symptom8);
           cHealthCondition = root.findViewById(R.id.healthComplication);
           cRecommendation = root.findViewById(R.id.status_info);
           cForward = root.findViewById(R.id.forwardButton);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ResultViewModel cResultViewModel = new ViewModelProvider(requireActivity()).get(ResultViewModel.class);
        cResultViewModel.getSelected().observe(getViewLifecycleOwner(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                cResult = result;
               setResultDetails(result);
            }
        });
        cForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forwardToNcdc(cResult);
            }
        });
    }

    private void forwardToNcdc(Result result) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"info@ncdc.gov.ng"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My Symptoms Report");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Please, below is a summary of the symptoms I" +
                " am experiencing. This data is generated with the Covid-19 Self Assessment App. " +
                "Please review the details and reply if I am a suitable candidate for Medical Test or not. " +
                "Thanks. " + "\n\n" +
                "\bMY DETAILS" + "\n\n" +
                "Name: " + result.getName() + "\n" +
                "State of residence: " + result.getState() + "\n" +
                "Date of assessment: " + result.getDateOfAssessment() + "\n" +
                "\n" +
                "Other Health Complications: " + result.getHealthCondition() + "\n\n" +
                "\bMY SYMPTOMS:\n\n" +
                "Difficulty in breathing or shortness of breath: " + result.getBreathing() + "\n\n" +
                "Chest pain or pressure: " + result.getChestPain() + "\n\n" +
                "Loss of Speech or movement: " + result.getSpeechLoss() + "\n\n" +
                "Loss of taste or smell senses: " + result.getTasteLoss() + "\n\n" +
                "High Fever: " + result.getFever() + "\n\n" +
                "Dry Cough: " + result.getDryCough() + "\n\n" +
                "Tiredness or fatigue: " + result.getTiredness() + "\n\n" +
                "Sore throat or runny nose: " +result.getSoreThroat() + "\n\n" +
                "Thanks in anticipation of your prompt response");

        Intent chooser = Intent.createChooser(emailIntent, "Select email app to send with");
        if (emailIntent.resolveActivity(requireActivity().getPackageManager()) != null)
            startActivity(chooser);
    }

    private void setResultDetails(Result result) {
           StringBuilder cNameBuilder = new StringBuilder();
           cNameBuilder.append("Hello! ");
           cNameBuilder.append(result.getName());
        cUserName.setText(cNameBuilder);

        if (result.getBreathing() == null) {
            cSymptom1.setText(R.string.no);
            cSymptom1.setTextColor(Color.BLUE);
        }else {
            cSymptom1.setText(R.string.yes);
            cSymptom1.setTextColor(Color.RED);
        }

        if (result.getChestPain() == null) {
            cSymptom2.setText(R.string.no);
            cSymptom2.setTextColor(Color.BLUE);
        } else {
            cSymptom2.setText(R.string.yes);
            cSymptom2.setTextColor(Color.RED);
        }

        if (result.getSpeechLoss() == null) {
            cSymptom3.setText(R.string.no);
            cSymptom3.setTextColor(Color.BLUE);
        } else {
            cSymptom3.setText(R.string.yes);
            cSymptom3.setTextColor(Color.RED);
        }

        if (result.getTasteLoss() == null) {
            cSymptom4.setText("No");
            cSymptom4.setTextColor(Color.BLUE);
        } else {
            cSymptom4.setText("Yes");
            cSymptom4.setTextColor(Color.RED);
        }

        if (result.getFever() == null) {
            cSymptom5.setText(R.string.no);
            cSymptom5.setTextColor(Color.BLUE);
        } else {
            cSymptom5.setText(R.string.yes);
            cSymptom5.setTextColor(Color.RED);
        }

        if (result.getDryCough() == null) {
            cSymptom6.setText(R.string.no);
            cSymptom6.setTextColor(Color.BLUE);
        } else {
            cSymptom6.setText(R.string.yes);
            cSymptom6.setTextColor(Color.RED);
        }

        if (result.getTiredness() == null) {
            cSymptom7.setText(R.string.no);
            cSymptom7.setTextColor(Color.BLUE);
        } else {
            cSymptom7.setText(R.string.yes);
            cSymptom7.setTextColor(Color.RED);
        }

        if (result.getSoreThroat() == null) {
            cSymptom8.setText(R.string.no);
            cSymptom8.setTextColor(Color.BLUE);
        } else {
            cSymptom8.setText(R.string.yes);
            cSymptom8.setTextColor(Color.RED);
        }

        if (result.getHealthCondition() == null){
            cHealthCondition.setText(R.string.no);
            cHealthCondition.setTextColor(Color.BLUE);
        } else {
            StringBuilder cHealthBuilder = new StringBuilder();
            cHealthBuilder.append("Health complication: ");
            cHealthBuilder.append(result.getHealthCondition());
            cHealthCondition.setText(cHealthBuilder);
            cHealthCondition.setTextColor(Color.RED);
        }
        StringBuilder status = new StringBuilder();
        status.append("Recommendations: ");
        status.append(result.getRecommendation());
        cRecommendation.setText(status);
    }

}