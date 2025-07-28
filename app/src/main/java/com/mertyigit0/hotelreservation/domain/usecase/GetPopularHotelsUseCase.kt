package com.mertyigit0.hotelreservation.domain.usecase

import com.mertyigit0.hotelreservation.domain.model.Hotel
import com.mertyigit0.hotelreservation.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularHotelsUseCase @Inject constructor(
    private val repository: HotelRepository
) {
    operator fun invoke(): Flow<List<Hotel>> {
        return repository.getPopularHotels()
    }
} 