package com.mertyigit0.hotelreservation.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertyigit0.hotelreservation.domain.model.Schedule
import com.mertyigit0.hotelreservation.domain.usecase.GetScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getScheduleUseCase: GetScheduleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScheduleUiState())
    val uiState: StateFlow<ScheduleUiState> = _uiState.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    private val _currentMonth = MutableStateFlow(LocalDate.now())
    @RequiresApi(Build.VERSION_CODES.O)
    val currentMonth: StateFlow<LocalDate> = _currentMonth.asStateFlow()

    init {
        loadSchedule()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onMonthChanged(newMonth: LocalDate) {
        _currentMonth.value = newMonth
    }

    private fun loadSchedule() {
        viewModelScope.launch {
            getScheduleUseCase().collect { schedule ->
                _uiState.value = _uiState.value.copy(
                    schedule = schedule,
                    isLoading = false
                )
            }
        }
    }
}

data class ScheduleUiState(
    val schedule: Schedule? = null,
    val isLoading: Boolean = true,
    val error: String? = null
) 