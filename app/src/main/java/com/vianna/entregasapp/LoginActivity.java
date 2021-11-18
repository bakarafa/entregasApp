package com.vianna.entregasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.vianna.entregasapp.model.dto.UserDTO;
import com.vianna.entregasapp.service.UserService;

public class LoginActivity extends AppCompatActivity {

    /*CLASSES DO LOGIN SEM RETROFIT
    service/UserService
    service/Task/LoginTask
    model/dto/InputLoginDTO   */




    TextInputLayout tilLogin, tilSenha;
    Button btnLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding();

        registraEventos();

        loginInserido();//só pra nao precisar digitar login e senha / APAGAR DEPOIS
    }

    private void loginInserido() {
        //        tilLogin.getEditText().setText("admin");//apagar
        //        tilSenha.getEditText().setText("123");//apagar

        tilLogin.getEditText().setText("admin@coelho.com.br");//apagar
        tilSenha.getEditText().setText("admin");//apagar
    }

    private void registraEventos() {
        btnLogar.setOnClickListener(clickLogar());
    }


    private View.OnClickListener clickLogar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = tilLogin.getEditText().getText().toString();
                String senha = tilSenha.getEditText().getText().toString();

                Intent itt = new Intent();

                UserDTO user = new UserService().logar(login, senha);//usa o serviço pra tentar pegar um userDTO (logado)//1//esse serviço é como se fosse a DAO

                if ( user != null) {//se user nao for nulo (ou seja, conseguiu logar)

                    //shared prefs ao logar
                    SharedPreferences prefs = getSharedPreferences("filmesApp",MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor = prefs.edit();
                    prefsEditor.putString("token", user.getAccess_token());
                    prefsEditor.commit();
                    //

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

    }
}