package com.mertyigit0.hotelreservation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertyigit0.hotelreservation.domain.model.Hotel
import com.mertyigit0.hotelreservation.domain.model.HotelCategory
import com.mertyigit0.hotelreservation.domain.usecase.GetNearbyHotelsUseCase
import com.mertyigit0.hotelreservation.domain.usecase.GetPopularHotelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNearbyHotelsUseCase: GetNearbyHotelsUseCase,
    private val getPopularHotelsUseCase: GetPopularHotelsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _selectedCategory = MutableStateFlow(HotelCategory.HOTEL)
    val selectedCategory: StateFlow<HotelCategory> = _selectedCategory.asStateFlow()

    init {
        loadData()
    }

    fun onCategorySelected(category: HotelCategory) {
        _selectedCategory.value = category
        // In a real app, you would filter hotels by category here
    }

    private fun loadData() {
        viewModelScope.launch {
            getNearbyHotelsUseCase().collect { hotels ->
                _uiState.value = _uiState.value.copy(
                    nearbyHotels = hotels,
                    isLoading = false
                )
            }
        }

        viewModelScope.launch {
            getPopularHotelsUseCase().collect { hotels ->
                _uiState.value = _uiState.value.copy(
                    popularHotels = hotels
                )
            }
        }
    }
}

data class HomeUiState(
    val nearbyHotels: List<Hotel> = emptyList(),
    val popularHotels: List<Hotel> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
) 