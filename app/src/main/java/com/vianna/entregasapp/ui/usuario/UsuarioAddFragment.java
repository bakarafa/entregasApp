package com.vianna.entregasapp.ui.usuario;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.vianna.entregasapp.R;
import com.vianna.entregasapp.model.dto.UsuarioDTO;
import com.vianna.entregasapp.service.UsuarioService;
import com.vianna.entregasapp.ui.entregas.EntregasFragment;
import com.vianna.entregasapp.ui.home.HomeFragment;
import com.vianna.entregasapp.util.Validations;

public class UsuarioAddFragment extends Fragment {

    TextInputLayout tilNome, tilCpf, tilIdade, tilTelefone, tilEndereco, tilEmail, tilSenha;
    Button btnSalvar;
    TextView tvTituloPaginaAddUser;

    String accessToken, navClicado;

    View rootView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_add_usuario, container, false);//define qual fragment será carregada ao abrir

        //-----acesso ao token
        SharedPreferences prefs = this.getActivity().getSharedPreferences("entregasApp", Context.MODE_PRIVATE);
        accessToken = prefs.getString("accessToken","");
        //-----

        binding();



        registraEventos();

        return rootView;
    }

    private void registraEventos() {
        btnSalvar.setOnClickListener(salvaUser());
    }

    private View.OnClickListener salvaUser() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Validations.validaCampoVazio(tilNome) ||
                        !Validations.validaCampoVazio(tilCpf) ||
                        !Validations.validaCampoVazio(tilIdade) ||
                        !Validations.validaCampoVazio(tilTelefone) ||
                        !Validations.validaCampoVazio(tilEndereco) ||
                        !Validations.validaCampoVazio(tilEmail) ||
                        !Validations.validaCampoVazio(tilSenha)){
                    return;
                }

                UsuarioDTO usuarioDTO = new UsuarioDTO(tilNome.getEditText().getText().toString(),
                        tilCpf.getEditText().getText().toString(),
                        tilIdade.getEditText().getText().toString(),
                        tilTelefone.getEditText().getText().toString(),
                        tilEndereco.getEditText().getText().toString(),
                        tilEmail.getEditText().getText().toString(),
                        tilSenha.getEditText().getText().toString());

                if (navClicado.equals("addMotoboy")){
                    new UsuarioService().salvaMotoboy(accessToken, usuarioDTO);
                } else {
                    new UsuarioService().salvaCliente(usuarioDTO);
                }

                Toast.makeText(getActivity(), "Cadastro realizado com sucesso!.", Toast.LENGTH_SHORT).show();

                trocaFragment();
//                getActivity().onBackPressed();
//                getActivity().onBackPressed();
            }
        };
    }

    private void trocaFragment() {
                   HomeFragment ent = new HomeFragment();
//            //--------
//            Bundle args = new Bundle();
//            args.putString("navClicado", "entregasAtuais");
//            ent.setArguments(args);
//            //--------

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.replace(R.id.nav_host_fragment_content_main,//inicia a transacao///indica aonde o fragmento será trocado
                    ent);//qual fragmento será inserido

            transaction.commit();
        }


    private void binding() {
        tilNome = rootView.findViewById(R.id.tilAddUserNome);
        tilCpf = rootView.findViewById(R.id.tilAddUserCpf);
        tilIdade = rootView.findViewById(R.id.tilAddUserIdade);
        tilTelefone = rootView.findViewById(R.id.tilAddUserTelefone);
        tilEndereco = rootView.findViewById(R.id.tilAddUserEndereco);
        tilEmail = rootView.findViewById(R.id.tilAddUserEmail);
        tilSenha = rootView.findViewById(R.id.tilAddUserSenha);
        btnSalvar = rootView.findViewById(R.id.btnAddUser);
        tvTituloPaginaAddUser = rootView.findViewById(R.id.tvNovoUsuarioTitulo);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onStart() {
        super.onStart();

        navClicado = getArguments().getString("navClicado");

        if (navClicado.equals("addMotoboy")){
            tvTituloPaginaAddUser.setText("Novo Motoboy");
        } else {
            tvTituloPaginaAddUser.setText("Novo Cliente");
        }

    }

}