package com.vianna.entregasapp.model.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

    private int id;
    private String nome,cpf, idade, telefone, endereco,email,senha;

//acho que cpf é int

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nome, String cpf, String idade, String telefone, String endereco, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
    }

    public UsuarioDTO(String nome, String cpf, String idade, String telefone, String endereco, String email, String senha, int id) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
        this.id = id;
    }

    public UsuarioDTO(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}


