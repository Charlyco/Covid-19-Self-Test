package com.example.covid_19selftest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
    private TextView totalSamplesValue;
    private TextView totalConfirmed;
    private TextView totalConfirmedValue;
    private TextView activeCases;
    private TextView activeCasesValue;
    private TextView dischargedCases;
    private TextView dischargedCasesValue;
    private TextView deaths;
    private TextView totalDeathsValue;
    private RecyclerView cStatesRecyclerView;
    private ProgressBar cProgressBar;
    private TextView cBreakdownByStates;
    private LinearLayout totalTitleLayout, totalValuesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Toolbar cToolbar = findViewById(R.id.statisticsToolbar);
        setSupportActionBar(cToolbar);

        totalTitleLayout = findViewById(R.id.linearLayout_total);
        totalValuesLayout = findViewById(R.id.total_values_layout);
        cProgressBar = findViewById(R.id.progressBar);
        totalSamples = findViewById(R.id.totalSamplesState);
        totalConfirmed = findViewById(R.id.totalConfirmedCases);
        activeCases = findViewById(R.id.activeCases);
        dischargedCases = findViewById(R.id.discharged);
        deaths = findViewById(R.id.deaths);

        totalSamplesValue = findViewById(R.id.samplesValue);
        totalConfirmedValue = findViewById(R.id.confirmed_cases_value);
        activeCasesValue = findViewById(R.id.activeCasesValue);
        dischargedCasesValue = findViewById(R.id.dischargedCasesValue);
        totalDeathsValue = findViewById(R.id.totalDeathsValue);

        cBreakdownByStates = findViewById(R.id.breakdown);
        cStatesRecyclerView = findViewById(R.id.statesRecyclerView);

        cProgressBar.setVisibility(View.VISIBLE);
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
                    totalTitleLayout.setVisibility(View.VISIBLE);
                    totalValuesLayout.setVisibility(View.VISIBLE);
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
        totalSamples.setText(R.string.total_samples);
        totalSamplesValue.setText((data.getTotalSamplesTested()));

        totalConfirmed.setText(R.string.total_confirmed);
        totalConfirmedValue.setText(data.getTotalConfirmedCases().toString());

        activeCases.setText(R.string.active_cases);
        activeCasesValue.setText(data.getTotalActiveCases().toString());

        dischargedCases.setText(R.string.total_discharged);
        dischargedCasesValue.setText(data.getDischarged().toString());

        deaths.setText(R.string.total_deaths);
        totalDeathsValue.setText(data.getDeath().toString());
    }
}