package com.vianna.entregasapp.service;

import android.util.Log;

import com.vianna.entregasapp.config.RetrofitConfig;
import com.vianna.entregasapp.model.dto.EntregaDTO;
import com.vianna.entregasapp.model.dto.UsuarioDTO;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;

public class UsuarioService {

    public void salvaMotoboy(String accessToken, UsuarioDTO usuario) {//adm
        Retrofit r = new RetrofitConfig().getRetrofit();//pega a variavel que é o servico a ser consumido
        IUsuariosService service = r.create(IUsuariosService.class);//utiliza o retrofit para criar dinamicamente uma classe que implementa a IFilmeService (logo precisa imlementar todos os metodos da interface)

        Call<UsuarioDTO> chamada = service.salvaNovoMotoboy("Bearer "+accessToken, usuario);//prepara para fazer a busca de todos of filmes

        try {
            chamada.execute().body();
        } catch (IOException e) {
            //
        }
    }

    public void salvaCliente(UsuarioDTO usuario) {//nao precisa estar logado
        Retrofit r = new RetrofitConfig().getRetrofit();//pega a variavel que é o servico a ser consumido
        IUsuariosService service = r.create(IUsuariosService.class);//utiliza o retrofit para criar dinamicamente uma classe que implementa a IFilmeService (logo precisa imlementar todos os metodos da interface)

        Call<UsuarioDTO> chamada = service.salvaNovoCliente(usuario);

        try {
            chamada.execute().body();
        } catch (IOException e) {
            //
        }
    }
}
