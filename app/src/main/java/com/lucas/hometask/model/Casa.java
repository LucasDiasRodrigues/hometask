package com.lucas.hometask.model;

import java.util.ArrayList;

public class Casa {
    private String id;
    private String nome;
    private String imagem;
    private ArrayList<Usuario> moradores;
    private ArrayList<Tarefa> tarefas;
    private ArrayList<Regra> regras;


    public Casa(String nome) {
        this.nome = nome;
    }

    public Casa() {
    }

    public Casa(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Usuario> getMoradores() {
        return moradores;
    }

    public void setMoradores(ArrayList<Usuario> moradores) {
        this.moradores = moradores;
    }

    public void addMorador(Usuario morador){
        if(moradores == null) moradores = new ArrayList<>();
        moradores.add(morador);
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
