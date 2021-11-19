package com.vianna.entregasapp.config;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();//logging
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);//logging
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();//logging
        httpClient.addInterceptor(logging);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.entregas.davesmartins.com.br/")//url base do servico
                .addConverterFactory(GsonConverterFactory.create())//conversor
                .client(httpClient.build())//logging
                .build();
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }
}