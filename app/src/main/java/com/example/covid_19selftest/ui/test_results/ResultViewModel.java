package com.example.covid_19selftest.ui.test_results;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.covid_19selftest.data.Result;

public class ResultViewModel extends ViewModel {
    private ResultRepository cResultRepository;
    LiveData<Result[]> cAllResults;

    public ResultViewModel(Application application) {
        cResultRepository = new ResultRepository(application);
        cAllResults = cResultRepository.loadAllResults();
    }
    public void insertResult(Result result){
        cResultRepository.insertResult(result);
    }
}