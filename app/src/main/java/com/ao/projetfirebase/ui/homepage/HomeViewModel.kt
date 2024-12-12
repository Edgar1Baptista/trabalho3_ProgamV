package com.ao.projetfirebase.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ao.projetfirebase.data.RepositorioDados
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HomeViewModel: ViewModel() {
    private val respositorio = RepositorioDados()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    val posts = respositorio.getPosts(userId)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

}