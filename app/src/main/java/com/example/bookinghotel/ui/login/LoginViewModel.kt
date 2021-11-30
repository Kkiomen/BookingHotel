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
        object Loading : LoginState()
        object Success : LoginState()
        data class Error(val message : String) : LoginState()
        object Empty : LoginState()
    }

    var email : String = ""
    var password : String = ""

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Empty)
    val loginState : StateFlow<LoginState> = _loginState

    fun loginUser() = CoroutineScope(Dispatchers.IO).launch {
        _loginState.value = LoginState.Loading
        try {
            loginRepository.authUser(email, password)
            withContext(Dispatchers.Main){
                _loginState.value = LoginState.Success

                Log.i("LOGIN SUCCESS", "user logged in")
            }
        }catch (e : Exception){
            withContext(Dispatchers.Main){
                _loginState.value = LoginState.Error("user could not logged in")

                Log.i("LOGIN FAILURE", "user could not logged in")
            }
        }
    }
}