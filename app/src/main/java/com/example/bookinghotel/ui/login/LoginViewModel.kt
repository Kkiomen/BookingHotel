package com.example.bookinghotel.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Exception
import javax.inject.Inject

/*
* udostepnia do fragmentu:
* pola email i password
* loginUser() -> metoda ktora jest odpowiedzialna za logowanie uzytkownika do systemu (async)
* */

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    sealed class LoginState(){
        object Empty : LoginState()
        object Success : LoginState()
        data class Error(val message : String) : LoginState()
    }

    //add validation
    var email : String = ""
    var password : String = ""

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Empty)
    val loginState : StateFlow<LoginState> = _loginState

    fun loginUser() = CoroutineScope(Dispatchers.IO).launch {
        try {
            loginRepository.authUser(email, password)
            withContext(Dispatchers.Main){
                //emit when operation ended with success
                _loginState.value = LoginState.Success
            }
        }catch (e : Exception){
            withContext(Dispatchers.Main){
                //emit when operation ended with failure
                _loginState.value = LoginState.Error(e.message.toString())
            }
        }
    }
}