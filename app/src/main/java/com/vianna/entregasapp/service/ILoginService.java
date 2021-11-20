package com.vianna.entregasapp.service;

import com.vianna.entregasapp.model.dto.LoginDTO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ILoginService {

    @FormUrlEncoded
    @POST("oauth/token")
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    public Call<LoginDTO> getUser(
            @Header("Authorization")String token,
            @Field("grant_type")String grant_type,
            @Field("username")String login,
            @Field("password")String senha
    );

}
