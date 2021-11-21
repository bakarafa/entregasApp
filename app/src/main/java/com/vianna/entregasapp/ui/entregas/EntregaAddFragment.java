package com.vianna.entregasapp.ui.entregas;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.vianna.entregasapp.MainActivity;
import com.vianna.entregasapp.R;
import com.vianna.entregasapp.model.dto.EntregaDTO;
import com.vianna.entregasapp.service.EntregaService;

import java.util.ArrayList;
import java.util.List;

public class EntregaAddFragment extends Fragment {

    TextInputLayout tilProduto, tilObs;
    Spinner spiOrigem, spiDestino;
    Button btnSolicitar;

    List<String>lista = new ArrayList<>();
    String origem, destino;
    String accessToken;
    EntregaDTO entregaDTO;



    View rootView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.entrega_add_fragment, container, false);//define qual fragment será carregada ao abrir

        //-----acesso ao token
        SharedPreferences prefs = this.getActivity().getSharedPreferences("entregasApp", Context.MODE_PRIVATE);
        accessToken = prefs.getString("accessToken","");
        //-----

        binding();

        registraEventos();

        return rootView;
    }

    private void registraEventos() {
        btnSolicitar.setOnClickListener(solicitarEntrega());
    }

    private View.OnClickListener solicitarEntrega() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //calcula o valor e pergunta se quer confirmar
                origem = spiOrigem.getSelectedItem().toString();
                destino = spiDestino.getSelectedItem().toString();
                entregaDTO = new EntregaService().calculaEntrega(accessToken, origem, destino);

                //alerta
                final AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("Solicitando entrega")
                        .setMessage("Valor: R$ "+entregaDTO.getPreco())
                        .setPositiveButton("Aceitar", null)
                        .setNegativeButton("Recusar", null)
                        .show();

                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);//se clicar em sim
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        salvaEntrega();

                        Toast.makeText(getActivity(), "Entrega solicitada!.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

//                        fabContinuaCadastro.setEnabled(true);
//                        fabContinuaCadastro.setVisibility(View.VISIBLE);


//                        Intent itn = new Intent(getApplicationContext(), CadastroCompeticaoActivity.class);

//                        viewCadastraCompeticao.launch(itn);
                    }
                });
            }
        };
    }

    private void salvaEntrega() {//após clicar em ok
        entregaDTO.setProduto(tilProduto.getEditText().getText().toString());
        entregaDTO.setObs(tilObs.getEditText().getText().toString());
        entregaDTO.setBairroOrigem(origem);
        entregaDTO.setBairroDestino(destino);

        new EntregaService().createEntrega(accessToken, entregaDTO);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onStart() {
        super.onStart();

//        entregaDTO = new EntregaDTO();

        preencheSpinner();

    }

    private void preencheSpinner() {
        lista.add("Centro");
        lista.add("São Mateus");
        lista.add("Grambery");
        lista.add("Bom Pastor");
        lista.add("Alto dos Passos");
        lista.add("Outros");

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, lista);

        spiOrigem.setAdapter(adapter);
        spiDestino.setAdapter(adapter);
    }

    private void binding() {
        tilProduto = rootView.findViewById(R.id.tilAddEntregaProduto);
        tilObs = rootView.findViewById(R.id.tilAddEntregaObs);
        spiDestino = rootView.findViewById(R.id.spiAddEntregasDestino);
        spiOrigem = rootView.findViewById(R.id.spiAddEntregasOrigem);
        btnSolicitar = rootView.findViewById(R.id.btnAddEntregasFazerPedido);
    }

}