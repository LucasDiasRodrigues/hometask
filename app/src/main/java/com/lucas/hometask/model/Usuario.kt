package com.lucas.hometask.model

import java.io.Serializable

class Usuario : Serializable {
    var id: String? = null
    var nome: String? = null
    var email: String? = null
    var idCasa: String? = null
    var imagem: String? = null

    constructor()

    constructor(nome: String?, email: String?) {
        this.nome = nome
        this.email = email
    }
}
