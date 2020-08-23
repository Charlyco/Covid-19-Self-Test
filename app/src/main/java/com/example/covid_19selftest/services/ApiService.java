package com.example.covid_19selftest.services;

import com.example.covid_19selftest.data.Api;
import com.example.covid_19selftest.data.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {
    @GET("/api")
    Call<Api> getApi();
}
