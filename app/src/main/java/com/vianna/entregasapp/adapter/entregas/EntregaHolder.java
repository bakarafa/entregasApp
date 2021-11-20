package com.vianna.entregasapp.adapter.entregas;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vianna.entregasapp.R;
import com.vianna.entregasapp.model.dto.EntregaDTO;

public class EntregaHolder extends RecyclerView.ViewHolder {

    TextView tvDadosEntrega;


    public EntregaHolder(@NonNull View itemView) {
        super(itemView);

        binding();
    }

    private void binding() {
        tvDadosEntrega = itemView.findViewById(R.id.tvEntregasDadosLinha);
    }

    public void preencheLinha(EntregaDTO entregaDTO) {
        tvDadosEntrega.setText("Entrega de "+entregaDTO.getProduto());
    }
}
