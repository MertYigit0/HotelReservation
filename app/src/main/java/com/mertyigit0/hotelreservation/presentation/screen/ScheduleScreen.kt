package com.mertyigit0.hotelreservation.presentation.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mertyigit0.hotelreservation.domain.model.Booking
import com.mertyigit0.hotelreservation.presentation.components.*
import com.mertyigit0.hotelreservation.presentation.viewmodel.ScheduleViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleScreen(
    viewModel: ScheduleViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onBookingClick: (Booking) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentMonth by viewModel.currentMonth.collectAsState()
    val schedule = uiState.schedule
    
    // Get selected dates from bookings
    val selectedDates = schedule?.bookings?.map { it.checkInDate } ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        ScheduleTopBar(
            onBackClick = onBackClick,
            onSettingsClick = onSettingsClick
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
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 80.dp) // Space for bottom navigation
            ) {
                // Calendar Component
                CalendarComponent(
                    currentMonth = currentMonth,
                    selectedDates = selectedDates,
                    onMonthChanged = viewModel::onMonthChanged
                )
                
                // My Schedule Section
                MyScheduleSection(
                    bookings = schedule?.bookings ?: emptyList(),
                    onBookingClick = onBookingClick
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MyScheduleSection(
    bookings: List<Booking>,
    onBookingClick: (Booking) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "My Schedule",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "See all",
                fontSize = 14.sp,
                color = Color(0xFF2196F3),
                modifier = Modifier.clickable { /* Navigate to see all */ }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            bookings.forEach { booking ->
                BookingCard(
                    booking = booking,
                    onBookingClick = onBookingClick
                )
            }
        }
    }
} 