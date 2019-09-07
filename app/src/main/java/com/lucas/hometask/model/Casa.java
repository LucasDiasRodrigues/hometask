package com.lucas.hometask.model;

import java.util.ArrayList;

public class Casa {
    private int id;
    private String nome;
    private ArrayList<Usuario> moradores;
    private ArrayList<Tarefa> tarefas;

    public Casa(String nome) {
        this.nome = nome;
    }

    public Casa(int id, String nome) {
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

    public ArrayList<Usuario> getMoradores() {
        return moradores;
    }

    public void setMoradores(ArrayList<Usuario> moradores) {
        this.moradores = moradores;
    }
}
