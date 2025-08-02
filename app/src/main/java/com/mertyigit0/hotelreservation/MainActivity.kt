package com.mertyigit0.hotelreservation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mertyigit0.hotelreservation.presentation.components.BottomNavItem
import com.mertyigit0.hotelreservation.presentation.screen.HomeScreen
import com.mertyigit0.hotelreservation.presentation.screen.HotelDetailScreen
import com.mertyigit0.hotelreservation.presentation.screen.ScheduleScreen
import com.mertyigit0.hotelreservation.ui.theme.HotelReservationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HotelReservationTheme {
                var showDetail by remember { mutableStateOf(false) }
                var selectedBottomNav by remember { mutableStateOf(BottomNavItem.HOME) }
                
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    when {
                        showDetail -> {
                            HotelDetailScreen(
                                onBackClick = { showDetail = false },
                                onMenuClick = { /* Handle menu */ },
                                onBookingClick = { /* Handle booking */ }
                            )
                        }
                        selectedBottomNav == BottomNavItem.SCHEDULE -> {
                            ScheduleScreen(
                                onBackClick = { selectedBottomNav = BottomNavItem.HOME },
                                onSettingsClick = { /* Handle settings */ },
                                onBookingClick = { /* Handle booking click */ }
                            )
                        }
                        else -> {
                            HomeScreen(
                                onHotelClick = { /* Navigate to detail */ showDetail = true },
                                onBottomNavSelected = { navItem -> selectedBottomNav = navItem }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HotelReservationTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun HotelDetailScreenPreview() {
    HotelReservationTheme {
        HotelDetailScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleScreenPreview() {
    HotelReservationTheme {
        ScheduleScreen()
    }
}