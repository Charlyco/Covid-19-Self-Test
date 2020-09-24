package com.example.covid_19selftest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
public class DatePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            Calendar cCalendar = Calendar.getInstance();
            final int year = cCalendar.get(Calendar.YEAR);
            final int month = cCalendar.get(Calendar.MONTH);
            final int date = cCalendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(requireActivity(), (DatePickerDialog.OnDateSetListener) requireActivity(), year, month, date);
    }
}
