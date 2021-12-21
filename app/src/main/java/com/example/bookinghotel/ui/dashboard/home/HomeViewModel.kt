package com.example.bookinghotel.ui.dashboard.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bookinghotel.data.models.Hotel
import com.example.bookinghotel.data.models.Room
import com.google.firebase.firestore.ktx.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel(){

    sealed class HomeState{
        object Empty : HomeState()
        object Success : HomeState()
        data class Error(val message : String) : HomeState()
    }

    //List of rooms
    val hotels : MutableList<Hotel> = mutableListOf()

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Empty)
    val homeState : StateFlow<HomeState> = _homeState

    //function returnd Hotel and List of Rooms
    fun listOfHotels() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val documents = homeRepository.findAllHotelsWithRooms()?.documents

            withContext(Dispatchers.Main){
                documents?.forEach{
                    it.toObject<Hotel>()?.let { it1 -> hotels.add(it1) }
                }
                _homeState.value = HomeState.Success
            }
        }catch (e : Exception){
            withContext(Dispatchers.Main){
                _homeState.value = HomeState.Error(e.message.toString())
            }
        }
    }

}