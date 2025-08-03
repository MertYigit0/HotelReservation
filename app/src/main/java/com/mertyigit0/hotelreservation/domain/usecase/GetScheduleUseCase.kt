package com.mertyigit0.hotelreservation.domain.usecase

import com.mertyigit0.hotelreservation.domain.model.Schedule
import com.mertyigit0.hotelreservation.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetScheduleUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    operator fun invoke(): Flow<Schedule> {
        return repository.getSchedule()
    }
} 