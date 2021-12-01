package com.vianna.entregasapp.adapter.entregas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.vianna.entregasapp.EntregaDetalheActivity;
import com.vianna.entregasapp.MainActivity;
import com.vianna.entregasapp.R;
import com.vianna.entregasapp.model.dto.EntregaDTO;
import com.vianna.entregasapp.service.EntregaService;
import com.vianna.entregasapp.ui.entregas.EntregasFragment;
import com.vianna.entregasapp.util.Validations;

import java.util.Locale;

public class EntregaHolder extends RecyclerView.ViewHolder {

    TextView tvDadosEntrega, tvValorEntrega, tvNumeroEntrega, tvStatus;
    EntregaDTO entregaDTO;
    Button btnAceitaEntrega, btnFinalizaEntrega;

    String accessToken, ROLE;

    EditText input = new EditText(itemView.getContext());//pro motoboy colocar comentario

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



        input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                input.post(new Runnable() {
                    @Override
                    public void run() {
                        InputMethodManager inputMethodManager= (InputMethodManager) itemView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
                    }
                });
            }
        });





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

        if (ROLE.equals("MOTOBOY")) {
            tvValorEntrega.setText("R$ " + String.format(Locale.US, "%.2f", new Validations().calcularGanhoMotoboy(entregaDTO.getPreco())));
        } else {
            tvValorEntrega.setText("R$ " + String.format(Locale.US, "%.2f", entregaDTO.getPreco()));
        }



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


                //alerta
                final AlertDialog dialog = new AlertDialog.Builder(itemView.getContext())
                        .setTitle("Aceitar a entrega #"+entregaDTO.getIdentrega()+"?")
                        .setMessage("Produto: "+entregaDTO.getProduto())
                        .setPositiveButton("Sim", null)
                        .setNegativeButton("Não", null)
                        .show();

                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);//se clicar em sim
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new EntregaService().aceitaEntrega(accessToken, entregaDTO);//salva

                        //chama detalhe entrega adicionada
                        Intent itt = new Intent(view.getContext(), EntregaDetalheActivity.class);
                        itt.putExtra("entregaLinha", entregaDTO);

                        resultLauncher.launch(itt);

//                        chamaListagemAtuais();

                        Toast.makeText(view.getContext(), "Entrega aceita com sucesso!",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

            }
        };
    }

    private View.OnClickListener finalizaEntrega() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (input.getParent() != null) {
                    ((ViewGroup) input.getParent()).removeView(input);
                }

                input.requestFocus();

                //alerta
                final AlertDialog dialog = new AlertDialog.Builder(itemView.getContext())
                        .setView(input)
                        .setTitle("Entrega #"+entregaDTO.getIdentrega())
                        .setMessage(
                                "Produto: "+entregaDTO.getProduto()+
//                                "\nCliente: "+entregaDTO.getCliente().getNome()+
                                "\nValor: "+String.format(Locale.US, "%.2f",new Validations().calcularGanhoMotoboy(entregaDTO.getPreco()))+
                                "\n\n\nComentário:")
                        .setPositiveButton("Encerrar", null)
                        .setNegativeButton("Voltar", null)
                        .show();


                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);//se clicar em sim
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new EntregaService().finalizaEntrega(accessToken, entregaDTO);

                        atualizaGanhoNavMenu();

                        chamaListagemAtuais();


                        Toast.makeText(view.getContext(), "Entrega encerrada com sucesso!!",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        };

    }



    private void atualizaGanhoNavMenu() {
        MainActivity act = (MainActivity)itemView.getContext();
        act.setaSaldo();
    }

//    ActivityResultLauncher<Intent> viewEntregaDetalhe = itemView.getContext().registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {//será executado ao retornar
//
////                    chamaListagemAtuais();
//                }
//            }
//    );

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

    private void chamaListagemHistorico() {

        EntregasFragment ent = new EntregasFragment();

        //--------
        Bundle args = new Bundle();
        args.putString("navClicado", "historico");
        ent.setArguments(args);
        //--------

        FragmentManager fragmentManager = ((AppCompatActivity)itemView.getContext()).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.nav_host_fragment_content_main,//inicia a transacao///indica aonde o fragmento será trocado
                ent);//qual fragmento será inserido

        transaction.commit();
    }

    private void chamaListagemAtuais() {

        EntregasFragment ent = new EntregasFragment();

        //--------
        Bundle args = new Bundle();
        args.putString("navClicado", "entregasAtuais");
        ent.setArguments(args);
        //--------

        FragmentManager fragmentManager = ((AppCompatActivity)itemView.getContext()).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.nav_host_fragment_content_main,//inicia a transacao///indica aonde o fragmento será trocado
                ent);//qual fragmento será inserido

        transaction.commit();
    }


}
