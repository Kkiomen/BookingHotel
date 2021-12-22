package com.example.bookinghotel.di

import com.example.bookinghotel.data.repostiories.HotelRoomRepository
import com.example.bookinghotel.domain.services.HotelSingleRoomService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {

    @Singleton
    @Provides
    fun provideHotelSingleRoomService(hotelRoomRepository: HotelRoomRepository) : HotelSingleRoomService = HotelSingleRoomService(hotelRoomRepository)


}