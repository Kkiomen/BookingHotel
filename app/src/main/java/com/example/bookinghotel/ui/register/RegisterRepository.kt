package com.example.bookinghotel.ui.register

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class RegisterRepository {

    private val auth = Firebase.auth

    suspend fun createUser(email : String, password : String){
        auth.createUserWithEmailAndPassword(email, password).await()
    }

}