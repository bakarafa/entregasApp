package com.vianna.entregasapp.model.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private String access_token, refresh_token, nome, email;

    public  String getToken(){
        return "Bearer "+access_token;
    }

    public UserDTO() {
    }

    public UserDTO(String acess_token, String refresh_token, String nome, String email) {
        this.access_token = acess_token;
        this.refresh_token = refresh_token;
        this.nome = nome;
        this.email = email;
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
}
