package com.example.covid_19selftest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.covid_19selftest.data.Result;
import com.example.covid_19selftest.ui.test_results.ResultViewModel;

public class TestActivity extends AppCompatActivity {
    private EditText cName, cDateOfBirth, cHealthCondition;
    private RadioGroup cHealthComplication;
    private Spinner cStateSpinner;
    private CheckBox cBreathing, cDryCough, cSpeechLoss, cFever, cChestPain, cTiredness;
    private ResultViewModel cResultViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        cResultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);

        cStateSpinner = findViewById(R.id.stateSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.state_entries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cStateSpinner.setAdapter(adapter);

        cName = findViewById(R.id.patientName);
        cDateOfBirth = findViewById(R.id.dateOfBirth);
        cHealthCondition = findViewById(R.id.healthCondition);
        cHealthComplication = findViewById(R.id.healthComplication);
        cBreathing = findViewById(R.id.breathingCheckBox);
        cChestPain = findViewById(R.id.chest_painCheckBox);
        cSpeechLoss = findViewById(R.id.speechLossCheckBox);
        cFever = findViewById(R.id.feverCheckBox);
        cDryCough = findViewById(R.id.dryCoughCheckBox);
        cTiredness = findViewById(R.id.tirednessCheckBox);
        cHealthComplication = findViewById(R.id.healthComplication);
    }

    public void submitTest(View view) {
        String cPatientName = cName.getText().toString();
        String cDoB = cDateOfBirth.getText().toString();
        String state = cStateSpinner.getSelectedItem().toString();
        String cHealthCond = "None";
        if (cHealthComplication.getCheckedRadioButtonId() == R.id.yesButton) {
             cHealthCond = cHealthCondition.getText().toString();
        }
        String cDiffBreathing = null;
        String chestPain = null;
        String speechLoss = null;
        String fever = null;
        String dryCough = null;
        String tiredness = null;
        String status;
        if (cBreathing.isChecked()) {
             cDiffBreathing = getString(R.string.difficulty_in_breathing);
        }
        if (cChestPain.isChecked()) {
            chestPain = getString(R.string.chest_pain);
        }
        if (cSpeechLoss.isChecked()) {
            speechLoss = getString(R.string.speech_loss);
        }
        if (cFever.isChecked()) {
            fever = getString(R.string.fever);
        }
        if (cDryCough.isChecked()) {
            dryCough = getString(R.string.dry_cough);
        }
        if (cTiredness.isChecked()) {
            tiredness = getString(R.string.tiredness);
        }
        if ((cBreathing.isChecked() && cChestPain.isChecked() && cSpeechLoss.isChecked()
        || (cBreathing.isChecked() && cChestPain.isChecked() && cFever.isChecked())
        || (cDryCough.isChecked() && cFever.isChecked() && cChestPain.isChecked() ))) {
            status = getString(R.string.positive);
        } else if ((cTiredness.isChecked() && cDryCough.isChecked())
        || (cTiredness.isChecked() && cFever.isChecked())) {
            status = getString(R.string.likely_positive);
        } else {
            status = getString(R.string.negative);
        }
        Result cResult = new Result(cPatientName, cDoB, state, cHealthCond, cDiffBreathing,
                chestPain, speechLoss, fever, dryCough, tiredness, status);
        cResultViewModel.insertResult(cResult);
        startActivity(new Intent(this, TestResults.class));
    }
}