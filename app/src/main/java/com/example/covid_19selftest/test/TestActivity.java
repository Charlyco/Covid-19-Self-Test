package com.example.covid_19selftest.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.covid_19selftest.DatePickerFragment;
import com.example.covid_19selftest.R;
import com.example.covid_19selftest.data.Result;
import com.example.covid_19selftest.ui.test_results.ResultViewModel;

public class TestActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText cName, cHealthCondition;
    public EditText cDateOfAssessment;
    private RadioGroup cHealthComplication;
    private Spinner cStateSpinner;
    private CheckBox cBreathing, cDryCough, cSpeechLoss, cTasteLoss, cFever, cChestPain, cTiredness, cSoreThroat;
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
        cDateOfAssessment = findViewById(R.id.dateOfAssessment);
        cHealthCondition = findViewById(R.id.healthCondition);
        cHealthComplication = findViewById(R.id.healthComplication);
        cBreathing = findViewById(R.id.breathingCheckBox);
        cChestPain = findViewById(R.id.chest_painCheckBox);
        cSpeechLoss = findViewById(R.id.speechLossCheckBox);
        cTasteLoss = findViewById(R.id.lossOfTasteCheckBox);
        cFever = findViewById(R.id.feverCheckBox);
        cDryCough = findViewById(R.id.dryCoughCheckBox);
        cTiredness = findViewById(R.id.tirednessCheckBox);
        cSoreThroat = findViewById(R.id.soreThroatCheckBox);
        cHealthComplication = findViewById(R.id.healthComplication);

        cDateOfAssessment.setInputType(0);
        cDateOfAssessment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment cDialogFragment = new DatePickerFragment();
                cDialogFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    /* the submitTest method still needs to be modified to include the use of health complication
       to determine status */
    public void submitTest(View view) {
        String cPatientName = cName.getText().toString();
        String cDoA = cDateOfAssessment.getText().toString();
        String state = cStateSpinner.getSelectedItem().toString();
        String cHealthCond = "None";
        if (cHealthComplication.getCheckedRadioButtonId() == R.id.yesButton) {
             cHealthCond = cHealthCondition.getText().toString();
        }
        String diffBreathing = null;
        String chestPain = null;
        String speechLoss = null;
        String tasteLoss = null;
        String fever = null;
        String dryCough = null;
        String tiredness = null;
        String soreThroat = null;
        String recommendation;
        if (cBreathing.isChecked()) {
             diffBreathing = "Yes";
        }
        if (cChestPain.isChecked()) {
            chestPain = "Yes";
        }
        if (cSpeechLoss.isChecked()) {
            speechLoss = "Yes";
        }
        if (cTasteLoss.isChecked()) {
            tasteLoss ="Yes";
        }
        if (cFever.isChecked()) {
            fever = "Yes";
        }
        if (cDryCough.isChecked()) {
            dryCough = "Yes";
        }
        if (cTiredness.isChecked()) {
            tiredness = "Yes";
        }
        if (cSoreThroat.isChecked()) {
            soreThroat = "Yes";
        }
        if ((cBreathing.isChecked() && cChestPain.isChecked() && cSpeechLoss.isChecked())
        || (cBreathing.isChecked() && cChestPain.isChecked() && cFever.isChecked())
        || (cDryCough.isChecked() && cFever.isChecked() && cChestPain.isChecked())
        || (cSoreThroat.isChecked() && cFever.isChecked() && cBreathing.isChecked())
        || (cTasteLoss.isChecked() && cChestPain.isChecked() && cFever.isChecked())
        || (cTasteLoss.isChecked() && cSoreThroat.isChecked() && cBreathing.isChecked())
        || (cSpeechLoss.isChecked() && cFever.isChecked() && cBreathing.isChecked())
        || (cSpeechLoss.isChecked() && cSoreThroat.isChecked() && cDryCough.isChecked())) {
            recommendation = getString(R.string.positive_recommendation);

        } else if ((cTiredness.isChecked() && cDryCough.isChecked())
        || (cTiredness.isChecked() && cFever.isChecked())
        || (cFever.isChecked() && cSoreThroat.isChecked())
        || (cFever.isChecked() && cSpeechLoss.isChecked())
        || (cDryCough.isChecked() && cFever.isChecked())
        || (cFever.isChecked() && cChestPain.isChecked())) {
            recommendation = getString(R.string.less_likely_positive_recommend);

        } else {
            recommendation = getString(R.string.negative);
        }
        Result cResult = new Result(cPatientName, cDoA, state, cHealthCond, diffBreathing,
                chestPain, speechLoss, tasteLoss, fever, dryCough, tiredness, soreThroat, recommendation);
        cResultViewModel.insertResult(cResult);
        startActivity(new Intent(this, TestResults.class));
        finish();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String cDate = i2 +
                "/" +
                i1 +
                "/" +
                i;
        cDateOfAssessment.setText(cDate);
        cDateOfAssessment.setInputType(1);
    }
}