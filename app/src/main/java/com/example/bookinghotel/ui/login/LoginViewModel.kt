package com.example.bookinghotel.ui.login

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookinghotel.ui.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    sealed class LoginState(){
        object Empty : LoginState()
        object Success : LoginState()
        data class Error(val message : String) : LoginState()
    }

    var email : String = ""
    var password : String = ""

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Empty)
    val loginState : StateFlow<LoginState> = _loginState

    fun loginUser() = CoroutineScope(Dispatchers.IO).launch {
        try {
            loginRepository.authUser(email, password)
            withContext(Dispatchers.Main){
                //emit operation ended with success value
                _loginState.value = LoginState.Success
            }
        }catch (e : Exception){
            withContext(Dispatchers.Main){
                //emit operation ended with failure value
                _loginState.value = LoginState.Error("user could not logged in")
            }
        }
    }
}