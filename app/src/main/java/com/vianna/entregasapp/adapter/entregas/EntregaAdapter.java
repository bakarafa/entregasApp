package com.vianna.entregasapp.adapter.entregas;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vianna.entregasapp.R;
import com.vianna.entregasapp.model.dto.EntregaDTO;

import java.util.List;

public class EntregaAdapter extends RecyclerView.Adapter<EntregaHolder> {

    private List<EntregaDTO> lista;

    public EntregaAdapter(List<EntregaDTO> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public EntregaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EntregaHolder(LayoutInflater.from(parent.getContext())//definindo em qual holder será injetada a linha
                .inflate(R.layout.linha_entregas, parent, false));//definindo qual arquivo xml (linha) será injetada no holder
    }

    @Override
    public void onBindViewHolder(@NonNull EntregaHolder holder, int position) {
        holder.preencheLinha(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
