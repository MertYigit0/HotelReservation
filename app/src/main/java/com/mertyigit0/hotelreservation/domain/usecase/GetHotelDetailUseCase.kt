package com.mertyigit0.hotelreservation.domain.usecase

import com.mertyigit0.hotelreservation.domain.model.HotelDetail
import com.mertyigit0.hotelreservation.domain.repository.HotelDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHotelDetailUseCase @Inject constructor(
    private val repository: HotelDetailRepository
) {
    operator fun invoke(hotelId: String): Flow<HotelDetail> {
        return repository.getHotelDetail(hotelId)
    }
} 