package com.example.bookinghotel.ui.dashboard.reservation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bookinghotel.domain.model.UserReservedRoom
import com.example.bookinghotel.ui.dashboard.home.HomeViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val reservationRepository: ReservationRepository
) : ViewModel(){

    sealed class ReservationState{
        object Empty : ReservationState()
        object Success : ReservationState()
        data class Error(val message : String) : ReservationState()
    }

    private val user = FirebaseAuth.getInstance().currentUser
    private val _reservationState = MutableStateFlow<ReservationState>(ReservationState.Empty)
    val reservationState : StateFlow<ReservationState> = _reservationState

    var userReservedRoomList : List<UserReservedRoom> = listOf()

    fun listOfUserRooms() = CoroutineScope(Dispatchers.IO).launch{
        try {
            reservationRepository.findAllUserRooms()

            userReservedRoomList = reservationRepository.userReservedRoomList
                .filter { userReservedRoom -> userReservedRoom.userId == user?.uid }
                .toMutableList()

            Log.d("userReservedRoom", userReservedRoomList.toString())

            withContext(Dispatchers.Main){
                _reservationState.value = ReservationState.Success
            }
        }catch (e : Exception){
            withContext(Dispatchers.Main){
                _reservationState.value = ReservationState.Error(e.message.toString())
            }
        }
        _reservationState.value = ReservationState.Empty
    }

}