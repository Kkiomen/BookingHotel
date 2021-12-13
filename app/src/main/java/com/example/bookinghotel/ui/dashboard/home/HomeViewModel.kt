package com.example.bookinghotel.ui.dashboard.home

import androidx.lifecycle.ViewModel
import com.example.bookinghotel.domain.models.HotelRoom
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

    //I will try use HotelRoom Model instead of Hotel
    //because in HotelRoom Model we have List<Room> and in Hotel Model we have List<Int> of Rooms ID
    //So it will be easier to have two data models in one Model (HotelRoom) than use them separately (Hotel, Room)
    //lukassakwa
    var hotels : MutableList<HotelRoom> = mutableListOf()

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Empty)
    val homeState : StateFlow<HomeState> = _homeState

    //Work in progress
    //As I wrote before I need to implement function which convert Hotel and Room Model to one model HotelRoom
    //Function will be in other class maybe in data class or service class
    //lukassakwa
    fun listOfHotels() = CoroutineScope(Dispatchers.IO).launch {
        try {
            hotels = homeRepository.findAllHotelRoom()

            withContext(Dispatchers.Main){
                _homeState.value = HomeState.Success
            }
        }catch (e : Exception){
            withContext(Dispatchers.Main){
                _homeState.value = HomeState.Error(e.message.toString())
            }
        }
    }

}