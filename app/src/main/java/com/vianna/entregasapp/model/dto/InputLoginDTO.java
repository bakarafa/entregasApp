package com.vianna.entregasapp.model.dto;

public class InputLoginDTO {//3

    private String login, senha;

    public InputLoginDTO() {
    }

    public InputLoginDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
