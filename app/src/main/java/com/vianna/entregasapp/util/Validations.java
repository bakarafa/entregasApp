package com.vianna.entregasapp.util;

import android.content.Context;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Validations {

    public static boolean validaCampoVazio(TextInputLayout campo) {//valida campos vazios
        String valor = campo.getEditText().getText().toString().trim();//pegar o valor (getText) do textInputLayout

        if(valor.isEmpty()){//se o campo bindado no valor estiver vazio
            campo.setError("*Obrigatório!");//mensagem de erro (precisa estar com o errorEnabled// checado)
            return false;//retorna falso
        }else{
            campo.setError(null);//limpa mensagem de erro
            return true;//retorna true
        }
    }

    public static boolean validaSpinnerVazio(Spinner campo, Context ctx) {//valida campos vazios
        String valor = campo.getSelectedItem().toString();//pegar o valor (getText) do textInputLayout

        if(valor.isEmpty()){//se o campo bindado no valor estiver vazio

            Toast.makeText(ctx,//um toast só pra exibir que deu bom
                    "Todos os campos devem ser preenchidos!", Toast.LENGTH_LONG).show();
            return false;//retorna falso
        }else{
            return true;//retorna true
        }
    }
}
