package com.example.bookinghotel.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bookinghotel.R
import com.example.bookinghotel.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginViewModel : LoginViewModel by viewModels()
    private lateinit var loginBinding : FragmentLoginBinding

    private lateinit var emailEditText : EditText
    private lateinit var passwordEditText : EditText
    private lateinit var loginButton : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        emailEditText = loginBinding.emailLoginEditText
        passwordEditText = loginBinding.passwordLoginEditText
        loginButton = loginBinding.loginBuutton

        loginButton.setOnClickListener(View.OnClickListener {
            loginViewModel.email = emailEditText.text.toString()
            loginViewModel.password = emailEditText.text.toString()

            loginViewModel.loginUser()
        });

        return loginBinding.root
    }

}