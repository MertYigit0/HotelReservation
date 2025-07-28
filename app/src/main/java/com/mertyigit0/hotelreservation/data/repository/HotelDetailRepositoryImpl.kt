package com.mertyigit0.hotelreservation.data.repository

import com.mertyigit0.hotelreservation.domain.model.Amenity
import com.mertyigit0.hotelreservation.domain.model.HotelDetail
import com.mertyigit0.hotelreservation.domain.repository.HotelDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HotelDetailRepositoryImpl @Inject constructor() : HotelDetailRepository {
    
    override fun getHotelDetail(hotelId: String): Flow<HotelDetail> = flow {
        emit(
            HotelDetail(
                id = hotelId,
                name = "The Aston Vill Hotel",
                address = "Alice Springs NT 0870, Australia",
                rating = 5.0f,
                pricePerNight = 200.7,
                mainImageUrl = "https://images.unsplash.com/photo-1566073771259-6a8506099945?w=800&h=600&fit=crop",
                description = "Aston Hotel, Alice Springs NT 0870, Australia is a modern hotel. elegant 5 star hotel overlooking the sea. perfect for a romantic, charming",
                amenities = listOf(
                    Amenity("1", "Free Wifi", "wifi"),
                    Amenity("2", "Free Breakfast", "breakfast"),
                    Amenity("3", "5.0", "star")
                ),
                previewImages = listOf(
                    "https://images.unsplash.com/photo-1571896349842-33c89424de2d?w=200&h=150&fit=crop",
                    "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?w=200&h=150&fit=crop",
                    "https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?w=200&h=150&fit=crop"
                )
            )
        )
    }
} 