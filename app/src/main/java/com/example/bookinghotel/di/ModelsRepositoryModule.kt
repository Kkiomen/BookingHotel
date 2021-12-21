package com.example.bookinghotel.di

import com.example.bookinghotel.data.repostiories.HotelRoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModelsRepositoryModule {

    @Singleton
    @Provides
    fun provideRoomRepository() : HotelRoomRepository = HotelRoomRepository()

}