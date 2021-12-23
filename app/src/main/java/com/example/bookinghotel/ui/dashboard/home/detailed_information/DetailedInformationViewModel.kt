package com.example.bookinghotel.ui.dashboard.home.detailed_information

import androidx.lifecycle.ViewModel
import com.example.bookinghotel.domain.model.HotelSingleRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailedInformationViewModel @Inject constructor() : ViewModel(){
    var hotelRoom : HotelSingleRoom? = null
}