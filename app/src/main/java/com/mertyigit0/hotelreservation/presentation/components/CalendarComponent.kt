package com.mertyigit0.hotelreservation.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarComponent(
    currentMonth: LocalDate,
    selectedDates: List<LocalDate> = emptyList(),
    onMonthChanged: (LocalDate) -> Unit = {},
    onDateSelected: (LocalDate) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Month header with navigation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { 
                onMonthChanged(currentMonth.minusMonths(1))
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous Month"
                )
            }
            
            Text(
                text = currentMonth.format(java.time.format.DateTimeFormatter.ofPattern("MMMM yyyy")),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            
            IconButton(onClick = { 
                onMonthChanged(currentMonth.plusMonths(1))
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next Month"
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Days of week header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("S", "M", "T", "W", "T", "F", "S").forEach { day ->
                Text(
                    text = day,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Calendar grid
        CalendarGrid(
            currentMonth = currentMonth,
            selectedDates = selectedDates,
            onDateSelected = onDateSelected
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun CalendarGrid(
    currentMonth: LocalDate,
    selectedDates: List<LocalDate>,
    onDateSelected: (LocalDate) -> Unit
) {
    val firstDayOfMonth = currentMonth.withDayOfMonth(1)
    val lastDayOfMonth = currentMonth.withDayOfMonth(currentMonth.lengthOfMonth())
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7 // Sunday = 0
    
    val daysInMonth = currentMonth.lengthOfMonth()
    val totalCells = 42 // 6 rows * 7 days
    
    val calendarDays = mutableListOf<CalendarDay>()
    
    // Add empty cells for days before the first day of the month
    repeat(firstDayOfWeek) {
        calendarDays.add(CalendarDay.Empty)
    }
    
    // Add days of the current month
    for (day in 1..daysInMonth) {
        val date = currentMonth.withDayOfMonth(day)
        val isSelected = selectedDates.contains(date)
        calendarDays.add(CalendarDay.Date(date, isSelected))
    }
    
    // Add empty cells to fill the grid
    while (calendarDays.size < totalCells) {
        calendarDays.add(CalendarDay.Empty)
    }
    
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Create 6 rows for the calendar
        repeat(6) { rowIndex ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Create 7 columns for each row
                repeat(7) { colIndex ->
                    val dayIndex = rowIndex * 7 + colIndex
                    if (dayIndex < calendarDays.size) {
                        val day = calendarDays[dayIndex]
                        when (day) {
                            is CalendarDay.Date -> {
                                CalendarDay(
                                    date = day.date,
                                    isSelected = day.isSelected,
                                    onClick = { onDateSelected(day.date) }
                                )
                            }
                            is CalendarDay.Empty -> {
                                Box(modifier = Modifier.size(32.dp))
                            }
                        }
                    } else {
                        Box(modifier = Modifier.size(32.dp))
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun CalendarDay(
    date: LocalDate,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .background(
                color = if (isSelected) Color(0xFF6200EE) else Color.Transparent,
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            fontSize = 14.sp,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}

sealed class CalendarDay {
    object Empty : CalendarDay()
    data class Date(val date: LocalDate, val isSelected: Boolean) : CalendarDay()
} 