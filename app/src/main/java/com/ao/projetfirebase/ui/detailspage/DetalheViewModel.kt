package com.ao.projetfirebase.ui.detailspage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ao.projetfirebase.data.Post
import com.ao.projetfirebase.data.RepositorioDados
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetalheViewModel(
    private val postId: String
) : ViewModel() {
    private val repositorio = RepositorioDados()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    var post = MutableStateFlow<Post?>(null)
        private set

    init {
        viewModelScope.launch {
            post.value = repositorio.getPostById(userId, postId)
        }
    }
}