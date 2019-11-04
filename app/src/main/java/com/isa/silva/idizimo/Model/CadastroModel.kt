package com.isa.silva.idizimo.Model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
        var name: String? = "",
        var email: String? = "",
        var cpf: String? = "",
        var telefone: String? = ""

)