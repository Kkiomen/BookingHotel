package com.example.bookinghotel.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.bookinghotel.R
import com.example.bookinghotel.databinding.FragmentLoginBinding
import com.example.bookinghotel.ui.dashboard.main.MainDashboardActivity
import com.example.bookinghotel.ui.register.RegisterFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


/*
* Fragment ma takie funkcjonalnosci:
* - logowanie uzytkownika do systemu
* Fragment implementuje dwa editTexty
* */

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginViewModel : LoginViewModel by viewModels()
    private lateinit var loginBinding : FragmentLoginBinding

    private lateinit var emailEditText : EditText
    private lateinit var passwordEditText : EditText
    private lateinit var register: TextView
    private lateinit var loginButton : AppCompatButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        emailEditText = loginBinding.emailLoginEditText
        passwordEditText = loginBinding.passwordLoginEditText
        register = loginBinding.RegisterReferencept2
        loginButton = loginBinding.loginBuutton

        loginButton.setOnClickListener(View.OnClickListener {
            loginViewModel.email = emailEditText.text.toString()
            loginViewModel.password = emailEditText.text.toString()

            loginViewModel.loginUser()
        });

        register.setOnClickListener{
            val fragmentManager: FragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.login_panel, RegisterFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        lifecycleScope.launchWhenCreated {
            loginViewModel.loginState.collect {
                when(it){
                    is LoginViewModel.LoginState.Success -> {
                        val intent = Intent(context, MainDashboardActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                    is LoginViewModel.LoginState.Error -> {
                        Log.i("LOGIN ERROR", it.message)
                    }
                    else -> Unit
                }
            }
        }

        return loginBinding.root
    }

}