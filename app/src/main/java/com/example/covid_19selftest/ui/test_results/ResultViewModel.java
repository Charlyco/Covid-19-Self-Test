package com.example.covid_19selftest.ui.test_results;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.covid_19selftest.data.Result;

import java.util.List;

public class ResultViewModel extends AndroidViewModel {
    private ResultRepository cResultRepository;
    LiveData<List<Result>> cAllResults;
    private final MutableLiveData<Result> selected = new MutableLiveData<>();
    public void selectResult(Result result) {
        selected.setValue(result);
    }
    public MutableLiveData<Result> getSelected() {
        return selected;
    }

    public ResultViewModel(Application application) {
        super(application);
        cResultRepository = new ResultRepository(application);
        cAllResults = cResultRepository.loadAllResults();
    }
    public void insertResult(Result result){
        cResultRepository.insertResult(result);
    }
    public LiveData<List<Result>> loadResults() {
        cResultRepository.loadAllResults();
        return cAllResults;
    }
    public void deleteResult(Result result) {
        cResultRepository.deleteResult(result);
    }
}