package com.mertyigit0.hotelreservation.domain.repository

import com.mertyigit0.hotelreservation.domain.model.Hotel
import com.mertyigit0.hotelreservation.domain.model.HotelCategory
import kotlinx.coroutines.flow.Flow

interface HotelRepository {
    fun getNearbyHotels(): Flow<List<Hotel>>
    fun getPopularHotels(): Flow<List<Hotel>>
    fun getHotelsByCategory(category: HotelCategory): Flow<List<Hotel>>
} 