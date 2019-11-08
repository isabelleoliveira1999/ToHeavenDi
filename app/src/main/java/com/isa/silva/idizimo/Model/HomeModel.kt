package com.isa.silva.idizimo.Model

data class Home(
        var autor: String? = "",
        var email: String? = "",
        var cpf: String? = "",
        var comentariosNumber: Int? = 0,
        var comentarios: List<Comentarios>,
        var curtidasNumber: Int? = 0

)