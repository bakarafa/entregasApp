package com.vianna.entregasapp.model.dto;

import java.io.Serializable;

public class EntregaDTO implements Serializable {

    private int identrega;
    private String produto, bairroOrigem, bairroDestino, obs, status;
    private double preco;
    private UsuarioDTO cliente, motoboy;

    private String regiaoDeOrigem, regiaoDeDestino;

    public EntregaDTO() {
    }

    public EntregaDTO(double preco) {
        this.preco = preco;
    }

    public EntregaDTO(String produto, String bairroOrigem, String bairroDestino, String obs, double preco) {
        this.produto = produto;
        this.bairroOrigem = bairroOrigem;
        this.bairroDestino = bairroDestino;
        this.obs = obs;
        this.preco = preco;
    }

    public EntregaDTO(int identrega, String produto, String status, double preco) {
        this.identrega = identrega;
        this.produto = produto;
        this.status = status;
        this.preco = preco;
    }

    public EntregaDTO(int identrega, String produto, String obs, String status, double preco, UsuarioDTO cliente, UsuarioDTO motoboy, String regiaoDeOrigem, String regiaoDeDestino) {
        this.identrega = identrega;
        this.produto = produto;
        this.obs = obs;
        this.status = status;
        this.preco = preco;
        this.cliente = cliente;
        this.motoboy = motoboy;
        this.regiaoDeOrigem = regiaoDeOrigem;
        this.regiaoDeDestino = regiaoDeDestino;
    }

    ///////////////
    public EntregaDTO(int identrega, String produto, String bairroOrigem, String bairroDestino, String obs, String status, double preco, UsuarioDTO cliente, UsuarioDTO motoboy, String regiaoDeOrigem, String regiaoDeDestino) {
        this.identrega = identrega;
        this.produto = produto;
        this.bairroOrigem = bairroOrigem;
        this.bairroDestino = bairroDestino;
        this.obs = obs;
        this.status = status;
        this.preco = preco;
        this.cliente = cliente;
        this.motoboy = motoboy;
        this.regiaoDeOrigem = regiaoDeOrigem;
        this.regiaoDeDestino = regiaoDeDestino;
    }

    public UsuarioDTO getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioDTO cliente) {
        this.cliente = cliente;
    }

    public UsuarioDTO getMotoboy() {
        return motoboy;
    }

    public void setMotoboy(UsuarioDTO motoboy) {
        this.motoboy = motoboy;
    }

    public String getRegiaoDeOrigem() {
        return regiaoDeOrigem;
    }

    public void setRegiaoDeOrigem(String regiaoDeOrigem) {
        this.regiaoDeOrigem = regiaoDeOrigem;
    }

    public String getRegiaoDeDestino() {
        return regiaoDeDestino;
    }

    public void setRegiaoDeDestino(String regiaoDeDestino) {
        this.regiaoDeDestino = regiaoDeDestino;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdentrega() {
        return identrega;
    }

    public void setIdentrega(int identrega) {
        this.identrega = identrega;
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
