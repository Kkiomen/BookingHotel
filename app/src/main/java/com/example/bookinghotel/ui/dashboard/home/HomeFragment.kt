package com.example.bookinghotel.ui.dashboard.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.bookinghotel.R
import com.example.bookinghotel.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel : HomeViewModel by viewModels()

    private lateinit var binding : FragmentHomeBinding

    private lateinit var textView : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        textView = binding.mainText

        viewModel.listOfHotels()

        lifecycleScope.launchWhenCreated {
            viewModel.homeState.collect{
                when(it){
                    is HomeViewModel.HomeState.Success -> {
                        //TODO:: Recycler View with list of hotels for this moment
                        //TODO:: Because for know vieModel download only list of Hotels
                        //TODO:: viewModel.hotels ->  list of Hotels from vieModel
                        //TODO:: viewModel.listOfHotels() -> method which download list of hotels from firebase and put them into hotels list in viewModel
                        //LukasSakwa
                        textView.text = viewModel.rooms[0].toString()
                        Log.i("hotelRoom", viewModel.hotels.toString())
                    }
                    is HomeViewModel.HomeState.Error -> {
                        //TODO:: Handle error on UI
                    }
                    else -> Unit
                }
            }
        }

        return binding.root
    }

}