package com.lucas.hometask.Model;

public class Tarefa {
    private int id;
    private String nome;
    private String descricao;
    private Usuario responsavel;
    private int dificuldade = DIFICULDADE_FACIL;
    private boolean concluida;
    private int periodicidade = PERI_SEM;

    public static final int DIFICULDADE_FACIL = 1;
    public static final int DIFICULDADE_MEDIO = 2;
    public static final int DIFICULDADE_DIFICIL = 3;

    public static final int PERI_DIA = 1;
    public static final int PERI_SEM = 2;
    public static final int PERI_MES = 3;


    public Tarefa(String nome, String descricao, int dificuldade, int periodicidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.setDificuldade(dificuldade);
        this.setPeriodicidade(periodicidade);
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        if ((dificuldade == DIFICULDADE_FACIL)
                || (dificuldade == DIFICULDADE_MEDIO)
                || (dificuldade == DIFICULDADE_DIFICIL)) {
            this.dificuldade = dificuldade;
        }
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public int getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(int periodicidade) {
        if ((periodicidade == PERI_DIA)
                || (periodicidade == PERI_SEM)
                || (periodicidade == PERI_MES)) {
            this.periodicidade = periodicidade;
        }
    }
}
