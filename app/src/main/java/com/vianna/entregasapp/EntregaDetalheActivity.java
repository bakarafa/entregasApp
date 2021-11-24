package com.vianna.entregasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vianna.entregasapp.model.dto.EntregaDTO;
import com.vianna.entregasapp.service.EntregaService;

import java.util.Locale;

public class EntregaDetalheActivity extends AppCompatActivity {
    TextView tvNumero, tvStatus, tvClienteMotboy, tvOrigem, tvDestino, tvProduto, tvObs, tvPreco;
    EntregaDTO entrega;
    //    int posicao;

    String accessToken, ROLE;
    //-----


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_detalhe);

        binding();

        //-----acesso ao token
        SharedPreferences prefs = getSharedPreferences("entregasApp", Context.MODE_PRIVATE);
        accessToken = prefs.getString("accessToken","");
        ROLE = prefs.getString("ROLE","");
        //-----

        entrega = (EntregaDTO) getIntent().getExtras().getSerializable("entregaLinha");//recebe via intent


        preeenche();
    }

    private void preeenche() {

//        if (!ROLE.equals("admin")){//se for adm nao precisa pegar os dados da entrega, pois objeto ja contém tudo
//            entrega = new EntregaService().getUmaEntregaCliente(accessToken, entrega.getIdentrega());
//        }

        if (ROLE.equals("MOTOBOY")){
            entrega = new EntregaService().getUmaEntregaMotoboy(accessToken, entrega.getIdentrega());
            tvClienteMotboy.setText("CLIENTE: "+entrega.getCliente().getNome());
        } else if (ROLE.equals("CLIENTE")){
            entrega = new EntregaService().getUmaEntregaCliente(accessToken, entrega.getIdentrega());
            if (entrega.getStatus().equals("AGUARDANDO")){
                tvClienteMotboy.setText("AGUARDANDO MOTOBOY");
            } else {
                tvClienteMotboy.setText("MOTOBOY: "+entrega.getMotoboy().getNome());
            }
        } else {
            if (entrega.getStatus().equals("AGUARDANDO")){
                tvClienteMotboy.setText("CLIENTE: "+entrega.getCliente().getNome());
            } else {
                tvClienteMotboy.setText("CLIENTE: "+entrega.getCliente().getNome()+" | MOTOBOY: "+entrega.getMotoboy().getNome());
            }
        }

        tvNumero.setText("#"+Integer.toString(entrega.getIdentrega()));


        tvStatus.setText(entrega.getStatus());
        if (entrega.getStatus().equals("TRANSITO")){
            tvStatus.setTextColor(Color.GREEN);
        } else if (entrega.getStatus().equals("AGUARDANDO")){
            tvStatus.setTextColor(Color.BLUE);
        }

        tvOrigem.setText("Origem: "+entrega.getRegiaoDeOrigem());
        tvDestino.setText("Destino: "+entrega.getRegiaoDeDestino());
        tvProduto.setText("Produto: "+entrega.getProduto());
        tvObs.setText("Obs: "+entrega.getObs());
        tvPreco.setText("Valor: R$ "+String.format(Locale.US, "%.1f",entrega.getPreco()));
    }

    private void binding() {
        tvNumero = findViewById(R.id.tvEntregaDetalheNumero);
        tvStatus = findViewById(R.id.tvEntregaDetalheStatus);
        tvClienteMotboy = findViewById(R.id.tvEntregaDetalheClienteMotoboy);
        tvOrigem = findViewById(R.id.tvEntregaDetalheOrigem);
        tvDestino = findViewById(R.id.tvEntregaDetalheDestino);
        tvProduto = findViewById(R.id.tvEntregaDetalheProduto);
        tvObs = findViewById(R.id.tvEntregaDetalheObs);
        tvPreco = findViewById(R.id.tvEntregaDetalhePreco);
    }
}