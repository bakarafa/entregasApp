package com.vianna.entregasapp.service;

import android.util.Log;

import com.vianna.entregasapp.config.RetrofitConfig;
import com.vianna.entregasapp.model.dto.EntregaDTO;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EntregaService {

    List<EntregaDTO> re;


    public List<EntregaDTO> listaTodasEntregas(String accessToken) {
        Retrofit r = new RetrofitConfig().getRetrofit();//pega a variavel que é o servico a ser consumido
        IEntregaService service = r.create(IEntregaService.class);//utiliza o retrofit para criar dinamicamente uma classe que implementa a IFilmeService (logo precisa imlementar todos os metodos da interface)

        Call<List<EntregaDTO>> chamada = service.getAllEntregas("Bearer "+accessToken);//prepara para fazer a busca de todos of filmes

//        Log.i("TODAS ENTREGAS", "Chamada: "+service.getAllEntregas("Bearer "+accessToken));

//        chamada.enqueue(new Callback<List<EntregaDTO>>() {//tenta executar a solicitacao
//            @Override
//            public void onResponse(Call<List<EntregaDTO>> call, Response<List<EntregaDTO>> response) {//se o servidor respondeu
//                if (response.isSuccessful()){//se retornou ok
//                    Log.i("TODAS ENTREGAS", "Caiu no isSuccessful.");
//                    List<EntregaDTO> re = response.body();
//                } else {
//                    Log.i("TODAS ENTREGAS", "Caiu no else do isSuccessful.");
//                }
//            }
//            @Override
//            public void onFailure(Call<List<EntregaDTO>> call, Throwable t) {//se nao respondeu
//                Log.i("TODAS ENTREGAS", "Caiu no onFailure.");
//            }
//        });
        try {
            Log.i("TODAS ENTREGAS", "Caiu no try.");
                return chamada.execute().body();
        } catch (IOException e) {
            Log.i("TODAS ENTREGAS", "Caiu no catch.");
            return null;
        }
    }
}
