package com.example.bookinghotel.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val registerViewModel : RegisterViewModel by viewModels()

    //private lateinit var registerBinding : FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        /*
            registerBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_register,
                container,
                false
            )
            return registerBinding.root
        */

        return ComposeView(requireContext()).apply {
            setContent {
                
            }
        }
    }
}