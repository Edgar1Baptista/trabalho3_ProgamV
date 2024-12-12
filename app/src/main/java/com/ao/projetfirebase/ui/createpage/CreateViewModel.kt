package com.ao.projetfirebase.ui.createpage

import androidx.lifecycle.ViewModel
import com.ao.projetfirebase.data.RepositorioDados
import com.google.firebase.auth.FirebaseAuth

class CreateViewModel: ViewModel() {
    private val repositorio = RepositorioDados()
    private val  userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""


    fun criarPost(descricao: String, imagem: String) {
        repositorio.savePost(userId, descricao, imagem)
    }
}