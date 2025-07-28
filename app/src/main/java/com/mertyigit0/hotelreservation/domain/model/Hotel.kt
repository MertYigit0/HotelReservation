package com.mertyigit0.hotelreservation.domain.model

data class Hotel(
    val id: String,
    val name: String,
    val address: String,
    val rating: Float,
    val pricePerNight: Double,
    val imageUrl: String,
    val category: HotelCategory
)

enum class HotelCategory {
    HOTEL, HOMESTAY, APART
} 