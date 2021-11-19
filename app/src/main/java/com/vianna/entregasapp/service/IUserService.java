package com.vianna.entregasapp.service;

import com.vianna.entregasapp.model.dto.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IUserService {

    @FormUrlEncoded
    @POST("oauth/token")
    public Call<UserDTO> getUser(
            @Header("Authorization")String token,
            @Field("grant_type")String grant_type,
            @Field("username")String login,
            @Field("password")String senha
    );

}
