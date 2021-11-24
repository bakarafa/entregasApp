package com.vianna.entregasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.vianna.entregasapp.model.dto.LoginDTO;
import com.vianna.entregasapp.service.LoginService;
import com.vianna.entregasapp.util.Validations;

public class LoginActivity extends AppCompatActivity {

    /*CLASSES DO LOGIN SEM RETROFIT
    service/UserService
    service/Task/LoginTask
    model/dto/InputLoginDTO   */




    TextInputLayout tilLogin, tilSenha;
    Button btnLogar, btnClient, btnAdm, btnMoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding();

        registraEventos();

//        loginInserido();//só pra nao precisar digitar login e senha / APAGAR DEPOIS
    }

    private void loginInserido() {
        //        tilLogin.getEditText().setText("admin");//apagar
        //        tilSenha.getEditText().setText("123");//apagar

        tilLogin.getEditText().setText("admin@coelho.com.br");//apagar
        tilSenha.getEditText().setText("admin");//apagar
    }

    private void registraEventos() {

        btnLogar.setOnClickListener(clickLogar());

        btnMoto.setOnClickListener(preencheMoto());//apagar
        btnAdm.setOnClickListener(preencheAdm());//apagar
        btnClient.setOnClickListener(preencheClient());//apagar
    }

    private View.OnClickListener preencheClient() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tilLogin.getEditText().setText("ze@ze");//apagar
                tilSenha.getEditText().setText("123");//apagar

            }
        };

    }

    private View.OnClickListener preencheAdm() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tilLogin.getEditText().setText("admin@coelho.com.br");//apagar
                tilSenha.getEditText().setText("admin");//apagar
            }
        };
    }

    private View.OnClickListener preencheMoto() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tilLogin.getEditText().setText("gu@gu");//apagar
                tilSenha.getEditText().setText("123");//apagar
            }
        };
    }


    private View.OnClickListener clickLogar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!Validations.validaCampoVazio(tilLogin) ||
                        !Validations.validaCampoVazio(tilSenha)){
                    return;
                }

                String login = tilLogin.getEditText().getText().toString();
                String senha = tilSenha.getEditText().getText().toString();

                Intent itt = new Intent();

                LoginDTO user = new LoginService().logar(login, senha);//usa o serviço pra tentar pegar um userDTO (logado)//1//esse serviço é como se fosse a DAO

                if ( user != null) {//se user nao for nulo (ou seja, conseguiu logar)



                    itt.putExtra("userLogado",user);
                    setResult(10, itt);//logou certinho

                }else{
                    setResult(15, itt);//deu ruim no login
                }
                finish();
            }
        };
    }

    private void binding() {
        tilLogin = findViewById(R.id.tilLogin);
        tilSenha = findViewById(R.id.tilSenha);
        btnLogar = findViewById(R.id.btnLogar);

        btnAdm = findViewById(R.id.btnAdm);//apagar
        btnClient = findViewById(R.id.btnClient);//apagar
        btnMoto = findViewById(R.id.btnMoto);//apagar

    }
}