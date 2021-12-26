package com.example.bookinghotel.ui.login

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

/*
* udostepnia do fragmentu:
* authUser() -> metoda ktora jest odpowiedzialna za logowanie uzytkownika do systemu
* i sprawdzanie czy uzytkownik rzeczywiscie ma zalozone konto
* */

class LoginRepository {
    private val auth = Firebase.auth

    suspend fun authUser(email : String, password : String){
        auth.signInWithEmailAndPassword(email, password).await()
    }
}