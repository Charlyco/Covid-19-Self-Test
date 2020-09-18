package com.example.covid_19selftest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19selftest.data.Api;
import com.example.covid_19selftest.data.Data;
import com.example.covid_19selftest.data.State;
import com.example.covid_19selftest.services.ApiService;
import com.example.covid_19selftest.services.ServiceBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Covid_19_stat extends AppCompatActivity {
    private TextView totalSamples;
    private TextView totalConfirmed;
    private TextView activeCases;
    private TextView dischargedCases;
    private TextView deaths;
    private RecyclerView cStatesRecyclerView;
    private ProgressBar cProgressBar;
    private TextView cBreakdownByStates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Toolbar cToolbar = findViewById(R.id.statisticsToolbar);
        setSupportActionBar(cToolbar);

        cProgressBar = findViewById(R.id.progressBar);
        totalSamples = findViewById(R.id.totalSamplesState);
        totalConfirmed = findViewById(R.id.totalConfirmedCases);
        activeCases = findViewById(R.id.activeCases);
        dischargedCases = findViewById(R.id.discharged);
        deaths = findViewById(R.id.deaths);
        cBreakdownByStates = findViewById(R.id.breakdown);
        cStatesRecyclerView = findViewById(R.id.statesRecyclerView);

        cProgressBar.setVisibility(View.VISIBLE);
        totalSamples.setVisibility(View.INVISIBLE);
        totalConfirmed.setVisibility(View.INVISIBLE);
        activeCases.setVisibility(View.INVISIBLE);
        dischargedCases.setVisibility(View.INVISIBLE);
        deaths.setVisibility(View.INVISIBLE);
        cBreakdownByStates.setVisibility(View.INVISIBLE);
        ApiService taskService = ServiceBuilder.buildService(ApiService.class);
        Call<Api> call = taskService.getApi();

        call.enqueue(new Callback<Api>() {
            @Override
            public void onResponse(@NotNull Call<Api> call, @NotNull Response<Api> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    Data cData = response.body().getData();
                    List<State> cStates = response.body().getData().getStates();
                    displayStates(cStates);
                    totalSamples.setVisibility(View.VISIBLE);
                    totalConfirmed.setVisibility(View.VISIBLE);
                    activeCases.setVisibility(View.VISIBLE);
                    dischargedCases.setVisibility(View.VISIBLE);
                    deaths.setVisibility(View.VISIBLE);
                    cBreakdownByStates.setVisibility(View.VISIBLE);
                    setTotalValues(cData);
                } else Log.d("Response: ", "Nothing returned");
                cProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(@NotNull Call<Api> call, @NotNull Throwable t) {
                Toast.makeText(Covid_19_stat.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                cProgressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void displayStates(List<State> states) {
        StatisticsAdapter cAdapter = new StatisticsAdapter(states, this);
        cStatesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cStatesRecyclerView.setAdapter(cAdapter);
    }

    private void setTotalValues(Data data) {
        StringBuilder samplesBuilder = new StringBuilder();
        samplesBuilder.append("Total Samples Tested:  ");
        samplesBuilder.append(data.getTotalSamplesTested());
        totalSamples.setText(samplesBuilder);

        StringBuilder confirmedCasesBuilder = new StringBuilder();
        confirmedCasesBuilder.append("Total Confirmed Cases:  ");
        confirmedCasesBuilder.append(data.getTotalConfirmedCases());
        totalConfirmed.setText(confirmedCasesBuilder);

        StringBuilder activeCasesBuilder = new StringBuilder();
        activeCasesBuilder.append("Total Active Cases:  ");
        activeCasesBuilder.append(data.getTotalActiveCases());
        activeCases.setText(activeCasesBuilder);

        StringBuilder dischargedBuilder = new StringBuilder();
        dischargedBuilder.append("Discharged:  ");
        dischargedBuilder.append(data.getDischarged());
        dischargedCases.setText(dischargedBuilder);

        StringBuilder deathsBuilder = new StringBuilder();
        deathsBuilder.append("Total Deaths:  ");
        deathsBuilder.append(data.getDeath());
        deaths.setText(deathsBuilder);
    }
}