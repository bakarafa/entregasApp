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
    String accessToken, ROLE;
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
        if (ROLE.equals("CLIENTE")){
            entregas = (ArrayList<EntregaDTO>) new EntregaService().getTodasEntregasClienteLogado(accessToken);
        } else if (ROLE.equals("MOTOBOY")){
            entregas = (ArrayList<EntregaDTO>) new EntregaService().getTodasEntregasMotoboyLogado(accessToken);
        } else {//ADM
            entregas = (ArrayList<EntregaDTO>) new EntregaService().getTodasEntregasTodosMotoboys(accessToken);
        }

        setaAdapter();

        Log.i("ENTREGAS", "RETORNO BASEADO NO ROLE: "+entregas.size());


        //        mensagem = getActivity().findViewById(R.id.text_home);//binding

//        mensagem.setText(getArguments().getString("nome"));//pega o "nome" que foi passado via arguments da MainActivity e coloca no campo de id text_home
    }


    private void setaAdapter() {
        EntregaAdapter adapter = new EntregaAdapter(entregas);
        rvListagemEntregas.setAdapter(adapter);

        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rvListagemEntregas.setLayoutManager(layout);
    }
}