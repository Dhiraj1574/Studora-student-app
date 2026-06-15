package com.studora.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studora.app.ui.theme.*

@Composable
fun AttendanceScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .statusBarsPadding()
            .padding(20.dp)
    ) {

        Text(
            text = "Attendance",
            fontSize = 30.sp,
            color = TextPrimary
        )

        Text(
            text = "Track your class attendance easily.",
            fontSize = 15.sp,
            color = ElectricIndigo,
            modifier = Modifier.padding(top = 6.dp)
        )

        AttendanceOverviewCard()

        Text(
            text = "Subject Attendance",
            fontSize = 20.sp,
            color = TextPrimary,
            modifier = Modifier.padding(top = 28.dp)
        )

        SubjectAttendanceCard(
            subject = "Data Structures",
            percentage = "82%"
        )

        SubjectAttendanceCard(
            subject = "Operating Systems",
            percentage = "76%"
        )

        SubjectAttendanceCard(
            subject = "DBMS",
            percentage = "91%"
        )

        SubjectAttendanceCard(
            subject = "Computer Networks",
            percentage = "68%"
        )
    }
}

@Composable
fun AttendanceOverviewCard() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = CardDark
        )
    ) {

        Column(
            modifier = Modifier.padding(24.dp)
        ) {

            Text(
                text = "Overall Attendance",
                fontSize = 16.sp,
                color = TextSecondary
            )

            Text(
                text = "79%",
                fontSize = 42.sp,
                color = ElectricIndigo,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "Keep it above 75% to stay safe.",
                fontSize = 13.sp,
                color = TextSecondary,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}

@Composable
fun SubjectAttendanceCard(
    subject: String,
    percentage: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = CardDark
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = subject,
                fontSize = 16.sp,
                color = TextPrimary
            )

            Text(
                text = percentage,
                fontSize = 16.sp,
                color = ElectricIndigo
            )
        }
    }
}