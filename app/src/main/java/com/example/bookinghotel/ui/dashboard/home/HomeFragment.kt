package com.example.bookinghotel.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookinghotel.R
import com.example.bookinghotel.databinding.FragmentHomeBinding
import com.example.bookinghotel.ui.adapters.HomeRoomsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel : HomeViewModel by viewModels()

    private lateinit var binding : FragmentHomeBinding

    private lateinit var roomsRecyclerView : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        roomsRecyclerView = binding.homeRoomsRecyclerview
        roomsRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.listOfHotels()

        lifecycleScope.launchWhenCreated {
            viewModel.homeState.collect{
                when(it){
                    is HomeViewModel.HomeState.Success -> {
                        //TODO:: Recycler View
                        roomsRecyclerView.adapter = HomeRoomsAdapter(viewModel.rooms)
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