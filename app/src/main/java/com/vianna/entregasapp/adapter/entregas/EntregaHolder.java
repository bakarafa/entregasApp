package com.vianna.entregasapp.adapter.entregas;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vianna.entregasapp.EntregaDetalheActivity;
import com.vianna.entregasapp.R;
import com.vianna.entregasapp.model.dto.EntregaDTO;

public class EntregaHolder extends RecyclerView.ViewHolder {

    TextView tvDadosEntrega;
    EntregaDTO entregaDTO;
    int posicao;
    private ActivityResultLauncher<Intent> resultLauncher;//tag:linha6 criar variavel que receberá o resultlauncher

    public EntregaHolder(@NonNull View itemView,
                         ActivityResultLauncher<Intent> resultLauncher){//tag:linha7 recebe o resultlauncher
        super(itemView);
        this.resultLauncher = resultLauncher;//tag:linha7.1

        binding();

        clickNaLinha(itemView);
    }

    //----------------
    private void clickNaLinha(View itemView) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent itt = new Intent(view.getContext(), EntregaDetalheActivity.class);
                itt.putExtra("entrega", entregaDTO);
                itt.putExtra("posicao", posicao);//tag:linhaRetorno 05-coloca a posicao da linha clicada tambem na intent

                resultLauncher.launch(itt);//tag:linha8 usa o resultlauncher
            }
        });
    }
    //----------------

    private void binding() {
        tvDadosEntrega = itemView.findViewById(R.id.tvEntregasDadosLinha);
    }

    public void preencheLinha(EntregaDTO entregaDTO, int posicao) {
        this.entregaDTO = entregaDTO;
        this.posicao = posicao;

        tvDadosEntrega.setText("Entrega de "+entregaDTO.getProduto());
    }
}
