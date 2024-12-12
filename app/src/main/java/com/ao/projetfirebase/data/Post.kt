package com.ao.projetfirebase.data

import java.time.LocalDate

data class Post(
    val id: String = "",
    val imagem: String,
    val descricao: String,
    val data: String = LocalDate.now().toString()
)
