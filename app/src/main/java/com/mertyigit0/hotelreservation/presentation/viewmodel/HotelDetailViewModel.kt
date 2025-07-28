package com.mertyigit0.hotelreservation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertyigit0.hotelreservation.domain.model.HotelDetail
import com.mertyigit0.hotelreservation.domain.usecase.GetHotelDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelDetailViewModel @Inject constructor(
    private val getHotelDetailUseCase: GetHotelDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HotelDetailUiState())
    val uiState: StateFlow<HotelDetailUiState> = _uiState.asStateFlow()

    init {
        loadHotelDetail()
    }

    private fun loadHotelDetail() {
        viewModelScope.launch {
            getHotelDetailUseCase("1").collect { hotelDetail ->
                _uiState.value = _uiState.value.copy(
                    hotelDetail = hotelDetail,
                    isLoading = false
                )
            }
        }
    }
}

data class HotelDetailUiState(
    val hotelDetail: HotelDetail? = null,
    val isLoading: Boolean = true,
    val error: String? = null
) 