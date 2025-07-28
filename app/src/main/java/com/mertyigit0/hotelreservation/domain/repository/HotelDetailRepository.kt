package com.mertyigit0.hotelreservation.domain.repository

import com.mertyigit0.hotelreservation.domain.model.HotelDetail
import kotlinx.coroutines.flow.Flow

interface HotelDetailRepository {
    fun getHotelDetail(hotelId: String): Flow<HotelDetail>
} 