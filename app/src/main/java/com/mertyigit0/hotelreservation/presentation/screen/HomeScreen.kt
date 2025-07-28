package com.mertyigit0.hotelreservation.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import com.mertyigit0.hotelreservation.domain.model.Hotel
import com.mertyigit0.hotelreservation.domain.model.HotelCategory
import com.mertyigit0.hotelreservation.presentation.components.*
import com.mertyigit0.hotelreservation.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onHotelClick: (Hotel) -> Unit = {},
    onNotificationClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    var selectedBottomNav by remember { mutableStateOf(BottomNavItem.HOME) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        TopBar(
            onNotificationClick = onNotificationClick
        )
        
        // Category Tabs
        CategoryTabs(
            selectedCategory = selectedCategory,
            onCategorySelected = viewModel::onCategorySelected
        )
        
        // Content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp) // Space for bottom navigation
        ) {
            // Near Location Section
            NearLocationSection(
                hotels = uiState.nearbyHotels,
                onHotelClick = onHotelClick
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Popular Hotel Section
            PopularHotelSection(
                hotels = uiState.popularHotels,
                onHotelClick = onHotelClick
            )
        }
        
        // Bottom Navigation
        BottomNavigation(
            selectedItem = selectedBottomNav,
            onItemSelected = { selectedBottomNav = it }
        )
    }
}

@Composable
private fun NearLocationSection(
    hotels: List<Hotel>,
    onHotelClick: (Hotel) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Near Location",
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
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(hotels) { hotel ->
                HotelCard(
                    hotel = hotel,
                    onHotelClick = onHotelClick,
                    onFavoriteClick = { /* Handle favorite */ }
                )
            }
        }
    }
}

@Composable
private fun PopularHotelSection(
    hotels: List<Hotel>,
    onHotelClick: (Hotel) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Popular Hotel",
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
            hotels.forEach { hotel ->
                PopularHotelCard(
                    hotel = hotel,
                    onHotelClick = onHotelClick
                )
            }
        }
    }
} 