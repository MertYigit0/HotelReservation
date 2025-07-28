package com.mertyigit0.hotelreservation.domain.model

data class HotelDetail(
    val id: String,
    val name: String,
    val address: String,
    val rating: Float,
    val pricePerNight: Double,
    val mainImageUrl: String,
    val description: String,
    val amenities: List<Amenity>,
    val previewImages: List<String>
)

data class Amenity(
    val id: String,
    val name: String,
    val icon: String
) 