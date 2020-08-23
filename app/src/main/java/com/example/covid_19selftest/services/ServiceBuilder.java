package com.example.covid_19selftest.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String URL = "https://covidnigeria.herokuapp.com";

    //Logger
    private static HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    //OkHttp
    private static OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .addInterceptor(logger);

    //Retrofit
    private static Retrofit.Builder cBuilder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build());

    private static Retrofit retrofit = cBuilder.build();

    public static <S> S buildService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }
}
