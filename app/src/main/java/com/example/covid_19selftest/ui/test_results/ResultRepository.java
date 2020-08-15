package com.example.covid_19selftest.ui.test_results;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.covid_19selftest.data.Result;
import com.example.covid_19selftest.data.ResultDao;
import com.example.covid_19selftest.data.ResultDataBase;

import java.util.List;

public class ResultRepository {
    private ResultDao cResultDao;
    private LiveData<List<Result>> cAllResults;

    ResultRepository(Application application) {
        ResultDataBase db = ResultDataBase.getDatabase(application);
        cResultDao = db.cResultDao();
        cAllResults = cResultDao.loadAllResults();
    }
    LiveData<List<Result>> loadAllResults() {
    return cAllResults;
    }

    public void insertResult(final Result result) {
        ResultDataBase.cDatabaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cResultDao.insertResult(result);
            }
        });
    }

}
