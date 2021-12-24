package com.example.bookinghotel.ui.dashboard.home.detailed_information

import androidx.lifecycle.ViewModel
import com.example.bookinghotel.domain.model.HotelSingleRoom
import com.example.bookinghotel.ui.dashboard.reservation.ReservationViewModel
import com.example.bookinghotel.ui.login.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailedInformationViewModel @Inject constructor(
    private val detailedinformationRepository: DetailedinformationRepository
) : ViewModel(){

    sealed class ReservationState(){
        object Empty : ReservationState()
        object Success : ReservationState()
        data class Error(val message : String) : ReservationState()
    }

    private val _reservationState = MutableStateFlow<ReservationState>(ReservationState.Empty)
    val reservationState : StateFlow<ReservationState> = _reservationState

    var hotelRoom : HotelSingleRoom? = null

    fun roomReservation() = CoroutineScope(Dispatchers.IO).launch{
        _reservationState.value = ReservationState.Empty
        try {
            hotelRoom?.let { detailedinformationRepository.roomReservation(it) }

            withContext(Dispatchers.Main){
                _reservationState.value = ReservationState.Success
            }
        }catch (e : Exception){
            withContext(Dispatchers.Main){
                _reservationState.value = ReservationState.Error(e.toString())
            }
        }
    }

}