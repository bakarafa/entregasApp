package com.vianna.entregasapp.model.dto;

import java.io.Serializable;

public class EntregaDTO implements Serializable {

    private String produto, bairroOrigem, bairroDestino, obs;
    private double preco;

    public EntregaDTO() {
    }

    public EntregaDTO(String produto, String bairroOrigem, String bairroDestino, String obs, double preco) {
        this.produto = produto;
        this.bairroOrigem = bairroOrigem;
        this.bairroDestino = bairroDestino;
        this.obs = obs;
        this.preco = preco;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getBairroOrigem() {
        return bairroOrigem;
    }

    public void setBairroOrigem(String bairroOrigem) {
        this.bairroOrigem = bairroOrigem;
    }

    public String getBairroDestino() {
        return bairroDestino;
    }

    public void setBairroDestino(String bairroDestino) {
        this.bairroDestino = bairroDestino;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
