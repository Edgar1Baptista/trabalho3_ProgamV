package com.ao.projetfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ao.projetfirebase.ui.cadastrarpage.CadastrarPage
import com.ao.projetfirebase.ui.createpage.CreatePage
import com.ao.projetfirebase.ui.detailspage.DetailsPage
import com.ao.projetfirebase.ui.detailspage.DetalheViewModel
import com.ao.projetfirebase.ui.homepage.HomePage
import com.ao.projetfirebase.ui.loginpage.LoginPage
import com.ao.projetfirebase.ui.theme.ProjetoFirebaseTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjetoFirebaseTheme {
                val navController = rememberNavController()
                val user = FirebaseAuth.getInstance().currentUser
                val startDestination = if (user != null) HomePage else LoginPage
                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    composable<CadastrarPage> {
                        CadastrarPage(
                            voltar = { navController.navigate(LoginPage) }
                        )
                    }
                    composable<CreatePage> {
                        CreatePage()
                    }
                    composable<DetailsPage> {
                        val postId = it.toRoute<DetailsPage>().id
                        DetailsPage(DetalheViewModel(postId))
                    }
                    composable<HomePage> {
                        HomePage(
                            criarPost = { navController.navigate(CreatePage) },
                            detalhes = { id -> navController.navigate(DetailsPage(id)) }
                        )
                    }
                    composable<LoginPage> {
                        LoginPage(
                            login = { navController.navigate(HomePage) },
                            cadastrar = { navController.navigate(CadastrarPage) }
                        )
                    }
                }
            }
        }
    }
}
