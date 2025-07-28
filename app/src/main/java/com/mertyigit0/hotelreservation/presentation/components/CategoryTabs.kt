package com.mertyigit0.hotelreservation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertyigit0.hotelreservation.domain.model.HotelCategory

@Composable
fun CategoryTabs(
    selectedCategory: HotelCategory,
    onCategorySelected: (HotelCategory) -> Unit
) {
    val categories = listOf(
        CategoryTab(HotelCategory.HOTEL, "Hotel", Icons.Default.Home),
        CategoryTab(HotelCategory.HOMESTAY, "Homestay", Icons.Default.Home),
        CategoryTab(HotelCategory.APART, "Apart", Icons.Default.Home)
    )
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { categoryTab ->
            CategoryTabItem(
                categoryTab = categoryTab,
                isSelected = selectedCategory == categoryTab.category,
                onClick = { onCategorySelected(categoryTab.category) }
            )
        }
    }
}

@Composable
private fun CategoryTabItem(
    categoryTab: CategoryTab,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFF2196F3) else Color.Transparent
    val textColor = if (isSelected) Color.White else Color.Gray
    val iconColor = if (isSelected) Color.White else Color.Gray
    
    Row(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = categoryTab.icon,
            contentDescription = categoryTab.title,
            tint = iconColor,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = categoryTab.title,
            color = textColor,
            fontSize = 14.sp
        )
    }
}

private data class CategoryTab(
    val category: HotelCategory,
    val title: String,
    val icon: ImageVector
) 