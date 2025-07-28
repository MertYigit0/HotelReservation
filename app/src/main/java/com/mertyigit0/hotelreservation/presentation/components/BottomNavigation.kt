package com.mertyigit0.hotelreservation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class BottomNavItem(val title: String, val icon: ImageVector) {
    HOME("Home", Icons.Default.Home),
    CALENDAR("Calendar", Icons.Default.DateRange),
    BOOKMARK("Bookmark", Icons.Default.Star),
    PROFILE("Profile", Icons.Default.Person)
}

@Composable
fun BottomNavigation(
    selectedItem: BottomNavItem = BottomNavItem.HOME,
    onItemSelected: (BottomNavItem) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem.values().forEach { item ->
            BottomNavItem(
                item = item,
                isSelected = selectedItem == item,
                onClick = { onItemSelected(item) }
            )
        }
    }
}

@Composable
private fun BottomNavItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = if (isSelected) Color(0xFF2196F3) else Color.Transparent,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = if (isSelected) Color.White else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = item.title,
            fontSize = 12.sp,
            color = if (isSelected) Color(0xFF2196F3) else Color.Gray
        )
    }
} 