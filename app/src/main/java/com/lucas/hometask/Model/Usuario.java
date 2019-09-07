package com.lucas.hometask.Model;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private int idCasa;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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


    public void setSenha(String senha) {
        if (senha.length() >= 6) {
            this.senha = senha;
        } else {
            //todo Tratamento de senha
        }


    }

    public int getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(int idCasa) {
        this.idCasa = idCasa;
    }
}
