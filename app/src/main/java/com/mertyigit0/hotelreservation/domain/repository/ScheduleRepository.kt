package com.mertyigit0.hotelreservation.domain.repository

import com.mertyigit0.hotelreservation.domain.model.Schedule
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getSchedule(): Flow<Schedule>
    fun getBookingsForDate(date: java.time.LocalDate): Flow<List<com.mertyigit0.hotelreservation.domain.model.Booking>>
} 