package com.vianna.entregasapp.adapter.entregas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vianna.entregasapp.EntregaDetalheActivity;
import com.vianna.entregasapp.R;
import com.vianna.entregasapp.model.dto.EntregaDTO;
import com.vianna.entregasapp.service.EntregaService;

import java.util.Locale;

public class EntregaHolder extends RecyclerView.ViewHolder {

    TextView tvDadosEntrega, tvValorEntrega, tvNumeroEntrega, tvStatus;
    EntregaDTO entregaDTO;
    Button btnAceitaEntrega, btnFinalizaEntrega;

    String accessToken, ROLE;

    int posicao;
    private ActivityResultLauncher<Intent> resultLauncher;//tag:linha6 criar variavel que receberá o resultlauncher

    public EntregaHolder(@NonNull View itemView,
                         ActivityResultLauncher<Intent> resultLauncher){//tag:linha7 recebe o resultlauncher
        super(itemView);
        this.resultLauncher = resultLauncher;//tag:linha7.1

        //-----acesso ao token
        SharedPreferences prefs = itemView.getContext().getSharedPreferences("entregasApp", Context.MODE_PRIVATE);
        accessToken = prefs.getString("accessToken","");
        ROLE = prefs.getString("ROLE","");
        //-----

        binding();



    }

    //----------------
    private void clickNaLinha(View itemView) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent itt = new Intent(view.getContext(), EntregaDetalheActivity.class);
                itt.putExtra("entregaLinha", entregaDTO);
                itt.putExtra("posicaoLinha", posicao);//tag:linhaRetorno 05-coloca a posicao da linha clicada tambem na intent

                resultLauncher.launch(itt);//tag:linha8 usa o resultlauncher
            }
        });
    }
    //----------------

    private void binding() {
        tvNumeroEntrega = itemView.findViewById(R.id.tvEntregasLinhaNumero);
        tvDadosEntrega = itemView.findViewById(R.id.tvEntregasDadosLinha);
        tvValorEntrega = itemView.findViewById(R.id.tvEntregasLinhaPreco);
        tvStatus = itemView.findViewById(R.id.tvStatusAdmOnly);
        btnAceitaEntrega = itemView.findViewById(R.id.btnAceitarEntregaMotoboy);
        btnFinalizaEntrega = itemView.findViewById(R.id.btnFinalizarEntregaMotoboy);
    }

    public void preencheLinha(EntregaDTO entregaDTO, int posicao) {
        this.entregaDTO = entregaDTO;
        this.posicao = posicao;

        tvNumeroEntrega.setText("#"+entregaDTO.getIdentrega());
        tvDadosEntrega.setText(entregaDTO.getProduto());
        tvValorEntrega.setText("R$ "+String.format(Locale.US, "%.1f",entregaDTO.getPreco()));

        enableBotoes();//motoboy
        atualizaBolinhaStatus();
        habilitaClickNaLinha();
        registraEventos();
    }

    private void registraEventos() {
        btnFinalizaEntrega.setOnClickListener(finalizaEntrega());
        btnAceitaEntrega.setOnClickListener(AceitaEntrega());
    }

    private View.OnClickListener AceitaEntrega() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EntregaService().aceitaEntrega(accessToken, entregaDTO);
                Toast.makeText(view.getContext(), "Entrega aceita!",
                        Toast.LENGTH_SHORT).show();
            }
        };
    }

    private View.OnClickListener finalizaEntrega() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EntregaService().finalizaEntrega(accessToken, entregaDTO);

                Toast.makeText(view.getContext(), "Entrega finalizada!",
                        Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void enableBotoes() {
        if (ROLE.equals("MOTOBOY") && entregaDTO.getStatus().equals("AGUARDANDO")){
            btnAceitaEntrega.setVisibility(View.VISIBLE);
            tvValorEntrega.setVisibility(View.INVISIBLE);
        } else if (ROLE.equals("MOTOBOY") && entregaDTO.getStatus().equals("TRANSITO")){
            btnFinalizaEntrega.setVisibility(View.VISIBLE);
        }
    }

    private void atualizaBolinhaStatus() {

        if(entregaDTO.getStatus().equals("TRANSITO")){
            tvStatus.setTextColor(Color.GREEN);
        } else if (entregaDTO.getStatus().equals("ENTREGUE")){
            tvStatus.setTextColor(Color.GRAY);
        } else {
            tvStatus.setTextColor(Color.BLUE);
        }
    }

    private void habilitaClickNaLinha() {
        if (!(ROLE.equals("MOTOBOY") && entregaDTO.getStatus().equals("AGUARDANDO"))){
            clickNaLinha(itemView);
        }
    }


}
