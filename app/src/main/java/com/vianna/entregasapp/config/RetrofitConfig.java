package com.vianna.entregasapp.config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.entregas.davesmartins.com.br/")//url base do servico
                .addConverterFactory(GsonConverterFactory.create())//conversor
                .build();
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }
}