package com.vianna.entregasapp.service;

import com.vianna.entregasapp.model.dto.EntregaDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IEntregaService {

//    @FormUrlEncoded

    @GET("entrega/admin")//adm
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    public Call<List<EntregaDTO>> getAllEntregas(@Header("Authorization")String token);//todas entregas (de todos status)

    @GET("entrega")//cliente
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    public Call<List<EntregaDTO>> getAllEntregasUsuario(@Header("Authorization")String token);//todas entregas do usuario logado (todos status)

    @GET("entrega/motoboy")//motooby
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    public Call<List<EntregaDTO>> getEntregasAguardando(@Header("Authorization")String token);//todas entregas status AGUARDANDO

    @GET("entrega/{id}")//cliente
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    public Call<EntregaDTO> getUmaEntrega(@Header("Authorization")String token,
                                                @Path("id") int idEntrega);//dados de uma unica entrega

    @GET("entrega/motoboy/my")//motoboy
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    public Call<List<EntregaDTO>> getAllEntregasMotoboy(@Header("Authorization")String token);//todas entregas do motoboy logado (todos status)

    @GET("entrega/{bairroA}/{bairroB}/preco")//cliente //motoboy
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    public Call<Double> getPreco(@Header("Authorization")String token,
                                                        @Path("bairroA") String bairroA,
                                                        @Path("bairroB") String bairroB);//todas entregas do motoboy logado (todos status)

    @POST("entrega")//cliente
//    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    public Call<EntregaDTO> salvaNovaEntrega(@Header("Authorization")String token,
                                          @Body EntregaDTO entrega);//cria uma nova entrega

//    @FormUrlEncoded
//    @POST("entrega")//cliente
//    @Headers({"Content-Type: application/x-www-form-urlencoded"})
//    public Call<EntregaDTO> salvaNovaEntrega(@Header("Authorization")String token,
//                                             @Field("produto") String produto,
//                                             @Field("bairroOrigem") String bairroOrigem,
//                                             @Field("bairroDestino") String bairroDestino,
//                                             @Field("obs") String obs);//cria uma nova entrega

    @PUT("entrega/motoboy/{id}/aceitar")//motoboy
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    public Call<EntregaDTO> aceitaEntrega(@Header("Authorization")String token,
                                          @Path("id") int idEntrega,
                                          @Body EntregaDTO entrega);//aceita uma entrega

    @PUT("entrega/motoboy/{id}/finalizar")//motoboy
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    public Call<EntregaDTO> finalizaEntrega(@Header("Authorization")String token,
                                          @Path("id") int idEntrega,
                                          @Body EntregaDTO entrega);//finaliza uma entrega
}
