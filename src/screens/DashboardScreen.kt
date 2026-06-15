package com.studora.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studora.app.ui.theme.*

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 14.dp)
    ) {
        Text("Good Evening,", fontSize = 18.sp, color = TextSecondary)
        Text("Student 👋", fontSize = 30.sp, color = TextPrimary)

        Text(
            text = "Student Life, Sorted.",
            fontSize = 15.sp,
            color = ElectricIndigo,
            modifier = Modifier.padding(top = 6.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OverviewCard("👥", "Attendance", "78%")
            OverviewCard("📋", "Pending\nTasks", "5")
            OverviewCard("📅", "Today’s\nClasses", "4")
            OverviewCard("⏰", "Upcoming\nDeadlines", "3")
        }

        SectionHeader("Today’s Timetable")

        InfoCard(height = 165.dp) {
            Text("09:00 AM  •  Data Structures", color = TextPrimary, fontSize = 15.sp)
            Text("Room 204", color = ElectricIndigo, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))

            DividerLine()

            Text("11:00 AM  •  Operating Systems", color = TextPrimary, fontSize = 15.sp)
            Text("Lab 3", color = ElectricIndigo, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
        }

        SectionHeader("Pending Tasks")

        InfoCard(height = 190.dp) {
            Text("UI Assignment Submission", color = TextPrimary, fontSize = 15.sp)
            Text("Due Tomorrow • High Priority", color = ElectricIndigo, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))

            DividerLine()

            Text("Prepare DBMS Viva Questions", color = TextPrimary, fontSize = 15.sp)
            Text("Due Friday • Medium Priority", color = ElectricIndigo, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun SectionHeader(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontSize = 20.sp, color = TextPrimary)
        Text("View All", fontSize = 14.sp, color = ElectricIndigo)
    }
}

@Composable
fun InfoCard(
    height: Dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(top = 16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            content = content
        )
    }
}

@Composable
fun DividerLine() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(TextSecondary.copy(alpha = 0.15f))
    )
}

@Composable
fun OverviewCard(
    icon: String,
    title: String,
    value: String
) {
    Card(
        modifier = Modifier
            .width(88.dp)
            .height(135.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            ElectricIndigo.copy(alpha = 0.22f),
                            CardDark
                        )
                    )
                )
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(icon, fontSize = 22.sp)
            Text(title, fontSize = 10.sp, color = TextPrimary)
            Text(value, fontSize = 22.sp, color = ElectricIndigo)
        }
    }
}