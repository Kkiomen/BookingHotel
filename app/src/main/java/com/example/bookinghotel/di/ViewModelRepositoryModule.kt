package com.example.bookinghotel.di

import com.example.bookinghotel.domain.repositories.HotelRoomRepository
import com.example.bookinghotel.ui.dashboard.home.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelRepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(hotelRoomRepository: HotelRoomRepository) : HomeRepository = HomeRepository(hotelRoomRepository)

}