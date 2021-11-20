package com.example.bookinghotel.ui.login

import androidx.lifecycle.ViewModel
import com.example.bookinghotel.ui.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    var email : String = ""
    var password : String = ""

    fun loginUser(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                loginRepository.authUser(email, password)
                withContext(Dispatchers.Main){
                    //TODO:: send info to UI (success) and move to main program activity
                }
            }catch (e : Exception){
                withContext(Dispatchers.Main){
                    //TODO:: send info to UI (error)
                }
            }
        }
    }

}