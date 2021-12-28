package com.example.bookinghotel.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bookinghotel.ui.login.LoginViewModel
import com.example.bookinghotel.ui.register.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
* udostepnia do fragmentu:
* pola email i password
* registerUser() -> metoda ktora jest odpowiedzialna za zarejestrowanie uzytkownika do systemu (async)
* */

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
) : ViewModel() {

    sealed class RegisterState(){
        object Empty : RegisterState()
        object Success : RegisterState()
        data class Error(val message : String) : RegisterState()
    }

    //add validation
    var email = ""
    var password = ""

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Empty)
    val registerState : StateFlow<RegisterState> = _registerState

    fun registerUser(){
        CoroutineScope(Dispatchers.IO).launch{
            _registerState.value = RegisterState.Empty
            try {
                registerRepository.createUser(email, password)
                withContext(Dispatchers.IO){
                    _registerState.value = RegisterState.Success
                }
            }catch (e : Exception){
                withContext(Dispatchers.Main){
                    _registerState.value = RegisterState.Error("Could not create user")
                }
            }
        }
    }

}