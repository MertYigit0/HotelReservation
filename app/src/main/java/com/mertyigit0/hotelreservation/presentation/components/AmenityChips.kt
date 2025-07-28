package com.mertyigit0.hotelreservation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertyigit0.hotelreservation.domain.model.Amenity

@Composable
fun AmenityChips(
    amenities: List<Amenity>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        amenities.forEach { amenity ->
            AmenityChip(amenity = amenity)
        }
    }
}

@Composable
private fun AmenityChip(
    amenity: Amenity
) {
    val icon = when (amenity.icon) {
        "wifi" -> Icons.Default.Star
        "breakfast" -> Icons.Default.Star
        "star" -> Icons.Default.Star
        else -> Icons.Default.Search
    }
    
    val iconColor = when (amenity.icon) {
        "star" -> Color(0xFFFFD700)
        else -> Color.Gray
    }
    
    Row(
        modifier = Modifier
            .background(
                color = Color.LightGray.copy(alpha = 0.3f),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = amenity.name,
            tint = iconColor,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = amenity.name,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
} 