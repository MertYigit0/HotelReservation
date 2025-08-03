package com.mertyigit0.hotelreservation.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.mertyigit0.hotelreservation.domain.model.Booking
import com.mertyigit0.hotelreservation.domain.model.Schedule
import com.mertyigit0.hotelreservation.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor() : ScheduleRepository {
    
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getSchedule(): Flow<Schedule> = flow {
        val bookings = listOf(
            Booking(
                id = "1",
                hotelName = "The Aston Vill Hotel",
                hotelImageUrl = "https://images.unsplash.com/photo-1566073771259-6a8506099945?w=200&h=150&fit=crop",
                checkInDate = LocalDate.of(2024, 3, 19),
                pricePerNight = 200.7,
                hotelId = "hotel1"
            ),
            Booking(
                id = "2",
                hotelName = "Golden Palace Hotel",
                hotelImageUrl = "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?w=200&h=150&fit=crop",
                checkInDate = LocalDate.of(2024, 3, 25),
                pricePerNight = 175.9,
                hotelId = "hotel2"
            )
        )
        
        emit(Schedule(bookings = bookings))
    }
    
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getBookingsForDate(date: LocalDate): Flow<List<Booking>> = flow {
        val allBookings = listOf(
            Booking(
                id = "1",
                hotelName = "The Aston Vill Hotel",
                hotelImageUrl = "https://images.unsplash.com/photo-1566073771259-6a8506099945?w=200&h=150&fit=crop",
                checkInDate = LocalDate.of(2024, 3, 19),
                pricePerNight = 200.7,
                hotelId = "hotel1"
            ),
            Booking(
                id = "2",
                hotelName = "Golden Palace Hotel",
                hotelImageUrl = "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?w=200&h=150&fit=crop",
                checkInDate = LocalDate.of(2024, 3, 25),
                pricePerNight = 175.9,
                hotelId = "hotel2"
            )
        )
        
        val filteredBookings = allBookings.filter { it.checkInDate == date }
        emit(filteredBookings)
    }
} 