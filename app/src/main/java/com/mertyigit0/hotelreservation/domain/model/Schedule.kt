package com.mertyigit0.hotelreservation.domain.model

import java.time.LocalDate

data class Schedule(
    val bookings: List<Booking>,
    val selectedDate: LocalDate? = null
)

data class Booking(
    val id: String,
    val hotelName: String,
    val hotelImageUrl: String,
    val checkInDate: LocalDate,
    val pricePerNight: Double,
    val hotelId: String
) 