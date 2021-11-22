package com.vianna.entregasapp.ui.entregas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vianna.entregasapp.R;
import com.vianna.entregasapp.adapter.entregas.EntregaAdapter;
import com.vianna.entregasapp.model.dto.EntregaDTO;
import com.vianna.entregasapp.service.EntregaService;

import java.util.ArrayList;

public class EntregasFragment extends Fragment {

    //-----
    RecyclerView rvListagemEntregas;
    ArrayList<EntregaDTO> entregas;
    String accessToken, ROLE, navClicado;
    //-----



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_entregas, container, false);//define qual fragment será carregada ao abrir
        rvListagemEntregas = rootView.findViewById(R.id.rvListagemEntregas);

        //-----acesso ao token
        SharedPreferences prefs = this.getActivity().getSharedPreferences("entregasApp", Context.MODE_PRIVATE);
        accessToken = prefs.getString("accessToken","");
        ROLE = prefs.getString("ROLE","");
        //-----


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onStart() {
        super.onStart();

        navClicado = getArguments().getString("navClicado");//botao que o cara clicou la fora

        buscaListaParaCarregar();//busca a lista de acordo com o ROLE e o botão clicado

        setaAdapter();

        Log.i("ENTREGAS", "RETORNO BASEADO NO ROLE: "+entregas.size());

    }

    private void buscaListaParaCarregar() {
        if (ROLE.equals("CLIENTE")){
            if (navClicado.equals("entregasAtuais")){
                entregas = (ArrayList<EntregaDTO>) new EntregaService().getEntregasAguardandoETransitoClienteLogado(accessToken);
            } else if (navClicado.equals("historico")){
                entregas = (ArrayList<EntregaDTO>) new EntregaService().getEntregasFinalizadasClienteLogado(accessToken);
            } else {
//                entregas = (ArrayList<EntregaDTO>) new EntregaService().getTodasEntregasClienteLogado(accessToken);//tudo
            }
        } else if (ROLE.equals("MOTOBOY")){
            if (navClicado.equals("entregasAtuais")){
                entregas = (ArrayList<EntregaDTO>) new EntregaService().getEntregasEmTransitoMotoboyLogado(accessToken);
            } else if (navClicado.equals("historico")){
                entregas = (ArrayList<EntregaDTO>) new EntregaService().getEntregasFinalizadasMotoboyLogado(accessToken);
            } else if (navClicado.equals("aguardando")){
                entregas = (ArrayList<EntregaDTO>) new EntregaService().getEntregasAguardando(accessToken);
            } else {
//                entregas = (ArrayList<EntregaDTO>) new EntregaService().getTodasEntregasMotoboyLogado(accessToken);
            }

        } else {//ADM
            if (navClicado.equals("entregasAtuais")){
                entregas = (ArrayList<EntregaDTO>) new EntregaService().getTodasEntregasAguardandoETransitoAdm(accessToken);
            } else if (navClicado.equals("historico")){
                entregas = (ArrayList<EntregaDTO>) new EntregaService().getTodasEntregasFinalziadasAdm(accessToken);
            } else {
//                entregas = (ArrayList<EntregaDTO>) new EntregaService().getTodasEntregasTodosMotoboys(accessToken);
            }
        }
    }


    private void setaAdapter() {
        EntregaAdapter adapter = new EntregaAdapter(entregas);
        rvListagemEntregas.setAdapter(adapter);

        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rvListagemEntregas.setLayoutManager(layout);
    }
}