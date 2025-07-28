package com.mertyigit0.hotelreservation.data.repository

import com.mertyigit0.hotelreservation.domain.model.Hotel
import com.mertyigit0.hotelreservation.domain.model.HotelCategory
import com.mertyigit0.hotelreservation.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor() : HotelRepository {
    
    override fun getNearbyHotels(): Flow<List<Hotel>> = flow {
        emit(listOf(
            Hotel(
                id = "1",
                name = "The Aston Vill Hotel",
                address = "Alice Springs NT 0870, Australia",
                rating = 5.0f,
                pricePerNight = 200.7,
                imageUrl = "https://images.unsplash.com/photo-1566073771259-6a8506099945?w=400&h=300&fit=crop",
                category = HotelCategory.HOTEL
            ),
            Hotel(
                id = "2",
                name = "Golden Pa",
                address = "Northern Territory, Australia",
                rating = 4.8f,
                pricePerNight = 175.9,
                imageUrl = "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?w=400&h=300&fit=crop",
                category = HotelCategory.HOTEL
            ),
            Hotel(
                id = "3",
                name = "Sunset Resort",
                address = "Darwin NT 0800, Australia",
                rating = 4.6f,
                pricePerNight = 220.0,
                imageUrl = "https://images.unsplash.com/photo-1571896349842-33c89424de2d?w=400&h=300&fit=crop",
                category = HotelCategory.HOTEL
            )
        ))
    }
    
    override fun getPopularHotels(): Flow<List<Hotel>> = flow {
        emit(listOf(
            Hotel(
                id = "4",
                name = "Asteria Hotel",
                address = "Wilora NT 0872, Australia",
                rating = 5.0f,
                pricePerNight = 165.3,
                imageUrl = "https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?w=400&h=300&fit=crop",
                category = HotelCategory.HOTEL
            ),
            Hotel(
                id = "5",
                name = "Ocean View Resort",
                address = "Katherine NT 0850, Australia",
                rating = 4.9f,
                pricePerNight = 185.5,
                imageUrl = "https://images.unsplash.com/photo-1571003123894-1f0594d2b5d9?w=400&h=300&fit=crop",
                category = HotelCategory.HOTEL
            )
        ))
    }
    
    override fun getHotelsByCategory(category: HotelCategory): Flow<List<Hotel>> = flow {
        val allHotels = listOf(
            Hotel(
                id = "1",
                name = "The Aston Vill Hotel",
                address = "Alice Springs NT 0870, Australia",
                rating = 5.0f,
                pricePerNight = 200.7,
                imageUrl = "https://images.unsplash.com/photo-1566073771259-6a8506099945?w=400&h=300&fit=crop",
                category = HotelCategory.HOTEL
            ),
            Hotel(
                id = "6",
                name = "Cozy Homestay",
                address = "Tennant Creek NT 0860, Australia",
                rating = 4.7f,
                pricePerNight = 120.0,
                imageUrl = "https://images.unsplash.com/photo-1564013799919-ab600027ffc6?w=400&h=300&fit=crop",
                category = HotelCategory.HOMESTAY
            ),
            Hotel(
                id = "7",
                name = "Modern Apartments",
                address = "Jabiru NT 0886, Australia",
                rating = 4.5f,
                pricePerNight = 150.0,
                imageUrl = "https://images.unsplash.com/photo-1545324418-cc1a3fa10c00?w=400&h=300&fit=crop",
                category = HotelCategory.APART
            )
        )
        
        emit(allHotels.filter { it.category == category })
    }
} 