package com.vianna.entregasapp.adapter.entregas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vianna.entregasapp.R;
import com.vianna.entregasapp.model.dto.EntregaDTO;

import java.util.List;

public class EntregaAdapter extends RecyclerView.Adapter<EntregaHolder> {

    private List<EntregaDTO> lista;
    private ActivityResultLauncher<Intent> resultLauncher;//tag:linha3 criar variavel que receberá o resultlauncher

    public EntregaAdapter(List<EntregaDTO> lista,
    ActivityResultLauncher<Intent> resultLauncher) {//tag:linha4 receber o resultlauncher - passar para a holder
        this.lista = lista;
        this.resultLauncher = resultLauncher;//tag:linha4.1
    }

    @NonNull
    @Override
    public EntregaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EntregaHolder(LayoutInflater.from(parent.getContext())//definindo em qual holder será injetada a linha
                .inflate(R.layout.linha_entregas, parent, false),//definindo qual arquivo xml (linha) será injetada no holder
                resultLauncher);//tag:linha5 passa o resultlauncher para ao holder em seu construtor
    }

    @Override
    public void onBindViewHolder(@NonNull EntregaHolder holder, int position) {
        holder.preencheLinha(lista.get(position),
                position);//------
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
