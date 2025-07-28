package com.mertyigit0.hotelreservation.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mertyigit0.hotelreservation.presentation.components.*
import com.mertyigit0.hotelreservation.presentation.viewmodel.HotelDetailViewModel

@Composable
fun HotelDetailScreen(
    viewModel: HotelDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onBookingClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val hotelDetail = uiState.hotelDetail

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        DetailTopBar(
            onBackClick = onBackClick,
            onMenuClick = onMenuClick
        )
        
        // Content
        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            hotelDetail?.let { detail ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 80.dp) // Space for booking button
                ) {
                    // Main Hotel Image
                    HotelMainImage(
                        imageUrl = detail.mainImageUrl,
                        onFavoriteClick = { /* Handle favorite */ }
                    )
                    
                    // Amenity Chips
                    AmenityChips(amenities = detail.amenities)
                    
                    // Hotel Information
                    HotelInfo(
                        name = detail.name,
                        address = detail.address,
                        pricePerNight = detail.pricePerNight
                    )
                    
                    // Description Section
                    DescriptionSection(description = detail.description)
                    
                    // Preview Section
                    PreviewSection(previewImages = detail.previewImages)
                    
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
        
        // Booking Button
        BookingButton(onBookingClick = onBookingClick)
    }
} 