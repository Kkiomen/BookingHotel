package com.example.bookinghotel.ui.register

import androidx.lifecycle.ViewModel
import com.example.bookinghotel.ui.register.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
) : ViewModel() {

    var email = ""
    var password = ""

    fun registerUser(){
        CoroutineScope(Dispatchers.IO).launch{
            try {
                registerRepository.createUser(email, password)
                withContext(Dispatchers.IO){
                    //TODO:: send info to user ui "Success to create user account"
                }
            }catch (e : Exception){
                withContext(Dispatchers.Main){
                    //TODO:: send info to user ui "Failed to create user account"
                }
            }
        }
    }

}