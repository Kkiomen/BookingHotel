package com.example.bookinghotel.ui.dashboard.reservation

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
import com.example.bookinghotel.databinding.FragmentReservationBinding
import com.example.bookinghotel.ui.adapters.ReservationRoomAdapter
import com.example.bookinghotel.ui.dashboard.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ReservationFragment : Fragment() {

    private val viewModel : ReservationViewModel by viewModels()

    private lateinit var binding : FragmentReservationBinding

    private lateinit var roomsRecyclerView : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_reservation,
            container,
            false
        )

        //Recycler view init

        val adapter = ReservationRoomAdapter(viewModel.userReservedRoomList)
        roomsRecyclerView = binding.userRoomsRecyclerview
        roomsRecyclerView.layoutManager = LinearLayoutManager(context)
        roomsRecyclerView.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.reservationState.collect{
                when(it){
                    is ReservationViewModel.ReservationState.Success -> {
                        adapter.setList(viewModel.userReservedRoomList)
                        adapter.notifyDataSetChanged()
                    }
                    is ReservationViewModel.ReservationState.Error -> {
                        //TODO:: Handle error on UI
                    }
                    else -> Unit
                }
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.listOfUserRooms()
    }

}