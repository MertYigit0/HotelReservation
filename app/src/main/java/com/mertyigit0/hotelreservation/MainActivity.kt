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
import com.mertyigit0.hotelreservation.presentation.screen.HomeScreen
import com.mertyigit0.hotelreservation.presentation.screen.HotelDetailScreen
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
                
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    if (showDetail) {
                        HotelDetailScreen(
                            onBackClick = { showDetail = false },
                            onMenuClick = { /* Handle menu */ },
                            onBookingClick = { /* Handle booking */ }
                        )
                    } else {
                        HomeScreen(
                            onHotelClick = { /* Navigate to detail */ showDetail = true }
                        )
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