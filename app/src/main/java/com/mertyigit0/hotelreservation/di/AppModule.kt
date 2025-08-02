package com.mertyigit0.hotelreservation.di

import com.mertyigit0.hotelreservation.data.repository.HotelDetailRepositoryImpl
import com.mertyigit0.hotelreservation.data.repository.HotelRepositoryImpl
import com.mertyigit0.hotelreservation.data.repository.ScheduleRepositoryImpl
import com.mertyigit0.hotelreservation.domain.repository.HotelDetailRepository
import com.mertyigit0.hotelreservation.domain.repository.HotelRepository
import com.mertyigit0.hotelreservation.domain.repository.ScheduleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideHotelRepository(): HotelRepository {
        return HotelRepositoryImpl()
    }
    
    @Provides
    @Singleton
    fun provideHotelDetailRepository(): HotelDetailRepository {
        return HotelDetailRepositoryImpl()
    }
    
    @Provides
    @Singleton
    fun provideScheduleRepository(): ScheduleRepository {
        return ScheduleRepositoryImpl()
    }
} 