package com.vianna.entregasapp.service;

import android.util.Log;

import com.vianna.entregasapp.config.RetrofitConfig;
import com.vianna.entregasapp.model.dto.EntregaDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class EntregaService {

    List<EntregaDTO> listaRetorno = new ArrayList<>();


    //METODOS ADM
    public List<EntregaDTO> getTodasEntregasTodosMotoboys(String accessToken) {//adm
        Retrofit r = new RetrofitConfig().getRetrofit();//pega a variavel que é o servico a ser consumido
        IEntregaService service = r.create(IEntregaService.class);//utiliza o retrofit para criar dinamicamente uma classe que implementa a IFilmeService (logo precisa imlementar todos os metodos da interface)

        Call<List<EntregaDTO>> chamada = service.getAllEntregas("Bearer "+accessToken);//prepara para fazer a busca de todos of filmes

        try {
            Log.i("TODAS ENTREGAS", "Caiu no try.");
                return chamada.execute().body();
        } catch (IOException e) {
            Log.i("TODAS ENTREGAS", "Caiu no catch.");
            return null;
        }
    }



    //METODOS MOTOBOY
    public List<EntregaDTO> getTodasEntregasMotoboyLogado(String accessToken) {//cliente
        Retrofit r = new RetrofitConfig().getRetrofit();
        IEntregaService service = r.create(IEntregaService.class);

        Call<List<EntregaDTO>> chamada = service.getAllEntregasMotoboy("Bearer "+accessToken);

        try {
            Log.i("TODAS ENTREGAS", "Caiu no try.");
            return chamada.execute().body();
        } catch (IOException e) {
            Log.i("TODAS ENTREGAS", "Caiu no catch.");
            return null;
        }
    }

    public List<EntregaDTO> getEntregasFinalizadasMotoboyLogado(String accessToken) {
        Retrofit r = new RetrofitConfig().getRetrofit();
        IEntregaService service = r.create(IEntregaService.class);

        Call<List<EntregaDTO>> chamada = service.getAllEntregasUsuario("Bearer "+accessToken);

        try {
            Log.i("TODAS ENTREGAS", "Caiu no try.");
            retornaEntregue(chamada.execute().body());
            return listaRetorno;
        } catch (IOException e) {
            Log.i("TODAS ENTREGAS", "Caiu no catch.");
            return null;
        }
    }

    public List<EntregaDTO> getEntregasEmTransitoMotoboyLogado(String accessToken) {//cliente
        Retrofit r = new RetrofitConfig().getRetrofit();
        IEntregaService service = r.create(IEntregaService.class);

        Call<List<EntregaDTO>> chamada = service.getAllEntregasUsuario("Bearer "+accessToken);

        try {
            Log.i("TODAS ENTREGAS", "Caiu no try.");
            retornaTransito(chamada.execute().body());
            return listaRetorno;
        } catch (IOException e) {
            Log.i("TODAS ENTREGAS", "Caiu no catch.");
            return null;
        }
    }

    public List<EntregaDTO> getEntregasAguardandoMotoboyLogado(String accessToken) {//cliente
        Retrofit r = new RetrofitConfig().getRetrofit();//pega a variavel que é o servico a ser consumido
        IEntregaService service = r.create(IEntregaService.class);//utiliza o retrofit para criar dinamicamente uma classe que implementa a IFilmeService (logo precisa imlementar todos os metodos da interface)

        Call<List<EntregaDTO>> chamada = service.getAllEntregasUsuario("Bearer "+accessToken);//prepara para fazer a busca de todos of filmes

        try {
            Log.i("TODAS ENTREGAS", "Caiu no try.");
            retornaAguardando(chamada.execute().body());
            return listaRetorno;
        } catch (IOException e) {
            Log.i("TODAS ENTREGAS", "Caiu no catch.");
            return null;
        }
    }







    //METODOS CLIENTE
    public List<EntregaDTO> getTodasEntregasClienteLogado(String accessToken) {//cliente
        Retrofit r = new RetrofitConfig().getRetrofit();
        IEntregaService service = r.create(IEntregaService.class);

        Call<List<EntregaDTO>> chamada = service.getAllEntregasUsuario("Bearer "+accessToken);

        try {
            Log.i("TODAS ENTREGAS", "Caiu no try.");
            return chamada.execute().body();
        } catch (IOException e) {
            Log.i("TODAS ENTREGAS", "Caiu no catch.");
            return null;
        }
    }

    public List<EntregaDTO> getEntregasFinalizadasClienteLogado(String accessToken) {
        Retrofit r = new RetrofitConfig().getRetrofit();
        IEntregaService service = r.create(IEntregaService.class);

        Call<List<EntregaDTO>> chamada = service.getAllEntregasUsuario("Bearer "+accessToken);

        try {
            Log.i("TODAS ENTREGAS", "Caiu no try.");
            retornaEntregue(chamada.execute().body());
            return listaRetorno;
        } catch (IOException e) {
            Log.i("TODAS ENTREGAS", "Caiu no catch.");
            return null;
        }
    }

    public List<EntregaDTO> getEntregasEmTransitoClienteLogado(String accessToken) {//cliente
        Retrofit r = new RetrofitConfig().getRetrofit();
        IEntregaService service = r.create(IEntregaService.class);

        Call<List<EntregaDTO>> chamada = service.getAllEntregasUsuario("Bearer "+accessToken);

        try {
            Log.i("TODAS ENTREGAS", "Caiu no try.");
            retornaTransito(chamada.execute().body());
            return listaRetorno;
        } catch (IOException e) {
            Log.i("TODAS ENTREGAS", "Caiu no catch.");
            return null;
        }
    }

    public List<EntregaDTO> getEntregasAguardandoClienteLogado(String accessToken) {//cliente
        Retrofit r = new RetrofitConfig().getRetrofit();//pega a variavel que é o servico a ser consumido
        IEntregaService service = r.create(IEntregaService.class);//utiliza o retrofit para criar dinamicamente uma classe que implementa a IFilmeService (logo precisa imlementar todos os metodos da interface)

        Call<List<EntregaDTO>> chamada = service.getAllEntregasUsuario("Bearer "+accessToken);//prepara para fazer a busca de todos of filmes

        try {
            Log.i("TODAS ENTREGAS", "Caiu no try.");
            retornaAguardando(chamada.execute().body());
            return listaRetorno;
        } catch (IOException e) {
            Log.i("TODAS ENTREGAS", "Caiu no catch.");
            return null;
        }
    }










    //FILTROS
    public void retornaEntregue(List<EntregaDTO> lista) {
        for (EntregaDTO ent:lista) {
            if (ent.getStatus().equals("ENTREGUE")){
                listaRetorno.add(ent);
            }
        }
    }

    public void retornaTransito(List<EntregaDTO> lista) {
        for (EntregaDTO ent:lista) {
            if (ent.getStatus().equals("ENTREGUE")){
                listaRetorno.add(ent);
            }
        }
    }

    public void retornaAguardando(List<EntregaDTO> lista) {
        for (EntregaDTO ent:lista) {
            if (ent.getStatus().equals("ENTREGUE")){
                listaRetorno.add(ent);
            }
        }
    }


}
