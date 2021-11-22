package com.vianna.entregasapp.service;

import com.vianna.entregasapp.model.dto.EntregaDTO;
import com.vianna.entregasapp.model.dto.UsuarioDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IUsuariosService {

    @POST("usuarios/motoboy")//admin
    public Call<UsuarioDTO> salvaNovoMotoboy(@Header("Authorization")String token,
                                          @Body UsuarioDTO novoMotoboy);//salva novo motoboy

    @POST("usuarios/cliente")//admin
    public Call<UsuarioDTO> salvaNovoCliente(@Body UsuarioDTO novoCliente);//salva novo cliente

//    @POST("usuarios/cliente")//admin
//    @Headers({"Content-Type: application/x-www-form-urlencoded"})
//    public Call<UsuarioDTO> salvaNovoCliente(@Header("Authorization")String token,
//                                             @Body UsuarioDTO novoCliente);//salva novo cliente
}
