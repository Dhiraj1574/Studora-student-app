package com.studora.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.studora.app.navigation.BottomNavigationBar
import com.studora.app.ui.screens.AttendanceScreen
import com.studora.app.ui.screens.DashboardScreen
import com.studora.app.ui.screens.ProfileScreen
import com.studora.app.ui.screens.TasksScreen
import com.studora.app.ui.screens.TimetableScreen
import com.studora.app.ui.theme.StudoraTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            StudoraTheme {

                var selectedRoute by remember {
                    mutableStateOf("home")
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    bottomBar = {

                        BottomNavigationBar(
                            selectedRoute = selectedRoute,

                            onItemClick = { route ->
                                selectedRoute = route
                            }
                        )
                    }

                ) { innerPadding ->

                    when (selectedRoute) {

                        "home" -> DashboardScreen(
                            modifier = Modifier.padding(innerPadding)
                        )

                        "timetable" -> TimetableScreen(
                            modifier = Modifier.padding(innerPadding)
                        )

                        "tasks" -> TasksScreen(
                            modifier = Modifier.padding(innerPadding)
                        )

                        "attendance" -> AttendanceScreen(
                            modifier = Modifier.padding(innerPadding)
                        )

                        "profile" -> ProfileScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}