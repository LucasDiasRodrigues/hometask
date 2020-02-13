package com.lucas.hometask.model

import java.util.ArrayList

class Casa {
    var id: String? = null
    var nome: String? = null
    var imagem: String? = null
    var moradores: ArrayList<Usuario>? = null
    private val tarefas: ArrayList<Tarefa>? = null
    private val regras: ArrayList<Regra>? = null


    constructor(nome: String) {
        this.nome = nome
    }

    constructor()

    constructor(id: String, nome: String) {
        this.id = id
        this.nome = nome
    }

    fun addMorador(morador: Usuario) {
        if (moradores == null) moradores = ArrayList()
        moradores!!.add(morador)
    }
}
