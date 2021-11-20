package com.vianna.entregasapp.model.dto;

import java.io.Serializable;

public class LoginDTO implements Serializable {

    private String access_token, refresh_token, nome, email, ROLE;

    public  String getBearerToken(){
        return "Bearer "+access_token;//palavra "Bearer " seguido do token
    }

    public LoginDTO() {
    }

    public LoginDTO(String access_token, String refresh_token, String nome, String email, String ROLE) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.nome = nome;
        this.email = email;
        this.ROLE = ROLE;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }
}
