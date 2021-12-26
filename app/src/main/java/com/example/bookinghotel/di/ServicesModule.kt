package com.example.bookinghotel.di

import com.example.bookinghotel.data.repostiories.HotelRepository
import com.example.bookinghotel.data.repostiories.RoomRepository
import com.example.bookinghotel.data.repostiories.UserReservationRepository
import com.example.bookinghotel.domain.services.HotelSingleRoomService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

/*
* Wstrzykiwanie serwisu odpowiedzialnego za cala logike aplikacji
* */

@Module
@InstallIn(ActivityComponent::class)
class ServicesModule {

    @Singleton
    @Provides
    fun provideHotelSingleRoomService(hotelRepository: HotelRepository, roomRepository : RoomRepository, userReservationRepository: UserReservationRepository) : HotelSingleRoomService
                        = HotelSingleRoomService(hotelRepository, roomRepository, userReservationRepository)


}