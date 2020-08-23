package com.example.covid_19selftest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.covid_19selftest.data.Api;
import com.example.covid_19selftest.data.Data;
import com.example.covid_19selftest.services.ApiService;
import com.example.covid_19selftest.services.ServiceBuilder;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GlobalStatistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_statistics);

        ApiService taskService = ServiceBuilder.buildService(ApiService.class);
        Call<Api> call = taskService.getApi();

        call.enqueue(new Callback<Api>() {
            @Override
            public void onResponse(@NotNull Call<Api> call, @NotNull Response<Api> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    Data cData = response.body().getData();
                TextView record = findViewById(R.id.response);
                    Log.d("Responce: ", cData.getDischarged().toString());
                    Log.d("Responce: ", response.body().getData().getDischarged().toString());
                record.setText(cData.getTotalSamplesTested());
                } else Log.d("Response: ", "Nothing returned");
            }

            @Override
            public void onFailure(@NotNull Call<Api> call, @NotNull Throwable t) {
                TextView record = findViewById(R.id.response);
                record.setText(t.getMessage());
            }
        });
    }
}