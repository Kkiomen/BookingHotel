package com.example.bookinghotel.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.compose.ui.platform.ComposeView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bookinghotel.R
import com.example.bookinghotel.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val registerViewModel : RegisterViewModel by viewModels()
    private lateinit var registerBinding : FragmentRegisterBinding

    private lateinit var emailEditText : EditText
    private lateinit var passwordEditText : EditText
    private lateinit var registerButton : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        registerBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register,
            container,
            false
        )

        emailEditText = registerBinding.emailRegisterEditText
        passwordEditText = registerBinding.passwordRegisterEditText
        registerButton = registerBinding.registerButton

        registerButton.setOnClickListener(View.OnClickListener {
            registerViewModel.email = emailEditText.text.toString()
            registerViewModel.password = emailEditText.text.toString()

            registerViewModel.registerUser()
        });

        return registerBinding.root

    }
}