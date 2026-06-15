package com.studora.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.studora.app.ui.theme.CardDark
import com.studora.app.ui.theme.ElectricIndigo
import com.studora.app.ui.theme.TextPrimary
import com.studora.app.ui.theme.TextSecondary

@Composable
fun BottomNavigationBar(
    selectedRoute: String,
    onItemClick: (String) -> Unit
) {
    NavigationBar(
        containerColor = CardDark
    ) {
        bottomNavItems.forEach { item ->

            val selectedColor =
                if (item.route == selectedRoute) ElectricIndigo else TextSecondary

            NavigationBarItem(
                selected = item.route == selectedRoute,
                onClick = {
                    onItemClick(item.route)
                },
                icon = {
                    when (item.route) {
                        "home" -> Icon(Icons.Default.Home, contentDescription = "Home", tint = selectedColor)
                        "timetable" -> Icon(Icons.Default.DateRange, contentDescription = "Timetable", tint = selectedColor)
                        "tasks" -> Icon(Icons.Default.CheckCircle, contentDescription = "Tasks", tint = selectedColor)
                        "attendance" -> Icon(Icons.Default.BarChart, contentDescription = "Attendance", tint = selectedColor)
                        "profile" -> Icon(Icons.Default.Person, contentDescription = "Profile", tint = selectedColor)
                    }
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (item.route == selectedRoute) TextPrimary else TextSecondary
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = ElectricIndigo,
                    selectedTextColor = TextPrimary,
                    unselectedIconColor = TextSecondary,
                    unselectedTextColor = TextSecondary,
                    indicatorColor = ElectricIndigo.copy(alpha = 0.15f)
                )
            )
        }
    }
}