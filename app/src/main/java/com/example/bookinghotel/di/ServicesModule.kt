package com.example.bookinghotel.di

import com.example.bookinghotel.data.repostiories.HotelRoomRepository
import com.example.bookinghotel.data.repostiories.UserReservationRepository
import com.example.bookinghotel.domain.services.HotelSingleRoomService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
* Wstrzykiwanie serwisu odpowiedzialnego za cala logike aplikacji
* */

@Module
@InstallIn(ActivityComponent::class)
class ServicesModule {

    @Singleton
    @Provides
    fun provideHotelSingleRoomService(hotelRoomRepository: HotelRoomRepository, userReservationRepository: UserReservationRepository) : HotelSingleRoomService
                        = HotelSingleRoomService(hotelRoomRepository, userReservationRepository)


}