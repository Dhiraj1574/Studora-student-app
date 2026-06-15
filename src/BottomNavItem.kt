package com.studora.app.navigation

data class BottomNavItem(
    val title: String,
    val route: String
)

val bottomNavItems = listOf(

    BottomNavItem(
        title = "Home",
        route = "home"
    ),

    BottomNavItem(
        title = "Timetable",
        route = "timetable"
    ),

    BottomNavItem(
        title = "Tasks",
        route = "tasks"
    ),

    BottomNavItem(
        title = "Attendance",
        route = "attendance"
    ),

    BottomNavItem(
        title = "Profile",
        route = "profile"
    )
)