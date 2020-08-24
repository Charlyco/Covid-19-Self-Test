package com.example.covid_19selftest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
    private StatisticsAdapter cAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_statistics);

        totalSamples = findViewById(R.id.totalSamplesState);
        totalConfirmed = findViewById(R.id.totalConfirmedCases);
        activeCases = findViewById(R.id.activeCases);
        dischargedCases = findViewById(R.id.discharged);
        deaths = findViewById(R.id.deaths);
        cStatesRecyclerView = findViewById(R.id.statesRecyclerView);

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
                    setTotalValues(cData);
                } else Log.d("Response: ", "Nothing returned");
            }

            @Override
            public void onFailure(@NotNull Call<Api> call, @NotNull Throwable t) {
                TextView record = findViewById(R.id.response);
                record.setText(t.getMessage());
            }
        });

    }

    private void displayStates(List<State> states) {
        cAdapter = new StatisticsAdapter(states, this);
        cStatesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cStatesRecyclerView.setAdapter(cAdapter);
    }

    private void setTotalValues(Data data) {
        StringBuilder samplesBuilder = new StringBuilder();
        samplesBuilder.append("Total Samples Testested: ");
        samplesBuilder.append(data.getTotalSamplesTested());
        totalSamples.setText(samplesBuilder);

        StringBuilder confirmedCasesBuilder = new StringBuilder();
        confirmedCasesBuilder.append("Total Confirmed Cases: ");
        confirmedCasesBuilder.append(data.getTotalConfirmedCases());
        totalConfirmed.setText(confirmedCasesBuilder);

        StringBuilder acttiveCasesBuilder = new StringBuilder();
        acttiveCasesBuilder.append("Total Active Cases: ");
        acttiveCasesBuilder.append(data.getTotalActiveCases());
        activeCases.setText(acttiveCasesBuilder);

        StringBuilder dischargedBuilder = new StringBuilder();
        dischargedBuilder.append("Discharged: ");
        dischargedBuilder.append(data.getDischarged());
        dischargedCases.setText(dischargedBuilder);

        StringBuilder deathsBuilder = new StringBuilder();
        deathsBuilder.append("Total Deaths: ");
        deathsBuilder.append(data.getDeath());
        deaths.setText(deathsBuilder);
    }
}