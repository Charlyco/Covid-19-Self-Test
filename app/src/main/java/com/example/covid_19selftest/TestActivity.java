package com.example.covid_19selftest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.covid_19selftest.data.Patient;

public class TestActivity extends AppCompatActivity {
    private EditText cName, cDateOfBirth, cHealthCondition;
    private RadioGroup cHealthComplication;
    private Spinner cStateSpinner;
    private CheckBox cBreathing, cDryCough, cSpeechLoss, cFever, cChestPain, cTiredness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

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
        String cDiffBreathing = "";
        String chestPain = "";
        String speechLoss = "";
        String fever = "";
        String dryCough = "";
        String tiredness = "";
        if (cBreathing.isChecked()) {
             cDiffBreathing = getString(R.string.difficulty_in_breathing);
        }
        if (cChestPain.isChecked()) {
            chestPain = "Chest Pain";
        }
        if (cSpeechLoss.isChecked()) {
            speechLoss = "Speech Loss";
        }
        if (cFever.isChecked()) {
            fever = "Fever";
        }
        if (cDryCough.isChecked()) {
            dryCough = "Dry Cough";
        }
        if (cTiredness.isChecked()) {
            tiredness = "Tiredness";
        }
        Patient cPatient = new Patient(cPatientName, cDoB, state, cHealthCond, cDiffBreathing,
                chestPain, speechLoss, fever, dryCough, tiredness);
    }
}