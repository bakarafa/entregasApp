package com.vianna.entregasapp.ui.entregas;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.vianna.entregasapp.EntregaDetalheActivity;
import com.vianna.entregasapp.R;
import com.vianna.entregasapp.model.dto.EntregaDTO;
import com.vianna.entregasapp.service.EntregaService;
import com.vianna.entregasapp.util.Validations;

import java.util.ArrayList;
import java.util.List;

public class EntregaAddFragment extends Fragment {

    TextInputLayout tilProduto, tilObs;
    Spinner spiOrigem, spiDestino;
    Button btnSolicitar;

    double preco;
    List<String>lista = new ArrayList<>();
    String origem, destino;
    String accessToken;

    View rootView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_entrega_add, container, false);//define qual fragment será carregada ao abrir

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

                if(!Validations.validaCampoVazio(tilProduto) ||
                        !Validations.validaCampoVazio(tilObs)){
                    return;
                }
                //calcula o valor e pergunta se quer confirmar
                origem = spiOrigem.getSelectedItem().toString();
                destino = spiDestino.getSelectedItem().toString();
                preco = new EntregaService().getPrecoDaEntrega(accessToken, origem, destino);

                //alerta
                final AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("RESUMO")
                        .setMessage("Produto: "+tilProduto.getEditText().getText().toString()+"\n\n" +
                                tilObs.getEditText().getText().toString()+"\n\n" +
                                "Origem: "+origem+"\n" +
                                "Destino: "+destino+"\n\n" +
                                "Valor: R$ "+ preco)
                        .setPositiveButton("Confirmar", null)
                        .setNegativeButton("Voltar", null)
                        .show();

                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);//se clicar em sim
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();


                        salvaEntrega();

//                        chamaListagem();
                    }
                });
            }


        };
    }

    private void salvaEntrega() {//após clicar em ok
        EntregaDTO entregaDTO = new EntregaDTO(tilProduto.getEditText().getText().toString(),
                origem,
                destino,
                tilObs.getEditText().getText().toString(),
                preco);

        entregaDTO = new EntregaService().createEntrega(accessToken, entregaDTO);

        Toast.makeText(getActivity(), "Entrega solicitada!.", Toast.LENGTH_SHORT).show();



        //chama detalhe entrega adicionada
        Intent itt = new Intent(getContext(), EntregaDetalheActivity.class);
        itt.putExtra("entregaLinha", entregaDTO);

        viewEntregaDetalhe.launch(itt);

    }

    private void chamaListagem() {

        EntregasFragment ent = new EntregasFragment();

        //--------
        Bundle args = new Bundle();
        args.putString("navClicado", "entregasAtuais");
        ent.setArguments(args);
        //--------

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.nav_host_fragment_content_main,//inicia a transacao///indica aonde o fragmento será trocado
                ent);//qual fragmento será inserido

        transaction.commit();
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

    ActivityResultLauncher<Intent> viewEntregaDetalhe = registerForActivityResult(//tag:linha01 criar launcher - passar para adapter
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {//será executado ao retornar

                    chamaListagem();
                }
            }
    );

    private void binding() {
        tilProduto = rootView.findViewById(R.id.tilAddEntregaProduto);
        tilObs = rootView.findViewById(R.id.tilAddEntregaObs);
        spiDestino = rootView.findViewById(R.id.spiAddEntregasDestino);
        spiOrigem = rootView.findViewById(R.id.spiAddEntregasOrigem);
        btnSolicitar = rootView.findViewById(R.id.btnAddEntregasFazerPedido);
    }

}