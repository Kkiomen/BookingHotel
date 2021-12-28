package com.example.bookinghotel.ui.register

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

/*
* udostepnia do fragmentu:
* createUser() -> metoda ktora jest odpowiedzialna za rejestracje uzytkownika do systemu
* i sprawdzanie czy uzytkownik rzeczywiscie ma zalozone konto
* */

class RegisterRepository {

    private val auth = Firebase.auth

    suspend fun createUser(email : String, password : String){
        auth.createUserWithEmailAndPassword(email, password).await()
    }

}