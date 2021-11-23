package com.vianna.entregasapp.ui.entregas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vianna.entregasapp.R;
import com.vianna.entregasapp.adapter.entregas.EntregaAdapter;
import com.vianna.entregasapp.model.dto.EntregaDTO;
import com.vianna.entregasapp.service.EntregaService;

import java.util.ArrayList;

public class EntregasFragment extends Fragment {


    //-----
    TextView tvTituloPagina;

    RecyclerView rvListagemEntregas;
    ArrayList<EntregaDTO> entregas;
    String accessToken, ROLE, navClicado;
    //-----



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_entregas, container, false);//define qual fragment será carregada ao abrir
        rvListagemEntregas = rootView.findViewById(R.id.rvListagemEntregas);

        tvTituloPagina = rootView.findViewById(R.id.rvListagemEntregasTitulo);

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
                tvTituloPagina.setText("Entregas em Andamento");
            } else if (navClicado.equals("historico")){
                entregas = (ArrayList<EntregaDTO>) new EntregaService().getEntregasFinalizadasClienteLogado(accessToken);
                tvTituloPagina.setText("Entregas Encerradas");
            } else {
//                entregas = (ArrayList<EntregaDTO>) new EntregaService().getTodasEntregasClienteLogado(accessToken);//tudo
            }
        } else if (ROLE.equals("MOTOBOY")){
            if (navClicado.equals("entregasAtuais")){
                entregas = (ArrayList<EntregaDTO>) new EntregaService().getEntregasEmTransitoMotoboyLogado(accessToken);
                tvTituloPagina.setText("Entregas em Andamento");
            } else if (navClicado.equals("historico")){
                entregas = (ArrayList<EntregaDTO>) new EntregaService().getEntregasFinalizadasMotoboyLogado(accessToken);
                tvTituloPagina.setText("Entregas Encerradas");
            } else if (navClicado.equals("aguardando")){
                entregas = (ArrayList<EntregaDTO>) new EntregaService().getEntregasAguardando(accessToken);
                tvTituloPagina.setText("Entregas Disponiveis");
            } else {
//                entregas = (ArrayList<EntregaDTO>) new EntregaService().getTodasEntregasMotoboyLogado(accessToken);
            }

        } else {//ADM
            if (navClicado.equals("entregasAtuais")){
                entregas = (ArrayList<EntregaDTO>) new EntregaService().getTodasEntregasAguardandoETransitoAdm(accessToken);
                tvTituloPagina.setText("Entregas em Andamento");
            } else if (navClicado.equals("historico")){
                entregas = (ArrayList<EntregaDTO>) new EntregaService().getTodasEntregasFinalziadasAdm(accessToken);
                tvTituloPagina.setText("Entregas Encerradas");
            } else {
//                entregas = (ArrayList<EntregaDTO>) new EntregaService().getTodasEntregasTodosMotoboys(accessToken);
            }
        }
    }


    private void setaAdapter() {
        EntregaAdapter adapter = new EntregaAdapter(entregas, viewEntregaDetalhe);//tag:linha2 passar para o adapter em seu construtor
        rvListagemEntregas.setAdapter(adapter);

        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rvListagemEntregas.setLayoutManager(layout);
    }


    ActivityResultLauncher<Intent> viewEntregaDetalhe = registerForActivityResult(//tag:linha01 criar launcher - passar para adapter
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {//será executado ao retornar

//                    if (result.getResultCode() == 10){
//                        int pos = result.getData().getExtras().getInt("posicaoLancouNota");//tag:linhaRetorno 09-recebe a posicao da linha alterada
//                        double nota = result.getData().getDoubleExtra("novaNota", 0.00);//tag:linhaRetorno 09-recebe o novo valor da nota
//                        ((ArtigoAdapter)recVieArtigos.getAdapter()).atualizaHolder(pos, nota);//tag:linhaRetorno 10-chama o metodo que ira
//
//                    }
                }
            }
    );


}