package com.studora.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studora.app.ui.theme.*

@Composable
fun ProfileScreen(
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
            text = "Profile",
            fontSize = 30.sp,
            color = TextPrimary
        )

        Text(
            text = "Manage your account and preferences.",
            fontSize = 15.sp,
            color = ElectricIndigo,
            modifier = Modifier.padding(top = 6.dp)
        )

        ProfileHeaderCard()

        Text(
            text = "Settings",
            fontSize = 20.sp,
            color = TextPrimary,
            modifier = Modifier.padding(top = 28.dp)
        )

        SettingItem("Edit Profile")
        SettingItem("Notifications")
        SettingItem("Appearance")
        SettingItem("Privacy & Security")
        SettingItem("About Studora")
    }
}

@Composable
fun ProfileHeaderCard() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = CardDark
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(
                        ElectricIndigo,
                        CircleShape
                    )
            )

            Column(
                modifier = Modifier.padding(start = 18.dp)
            ) {

                Text(
                    text = "Student name",
                    fontSize = 20.sp,
                    color = TextPrimary
                )

                Text(
                    text = "Department",
                    fontSize = 14.sp,
                    color = TextSecondary,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun SettingItem(
    title: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp),

        shape = RoundedCornerShape(18.dp),

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
                text = title,
                fontSize = 16.sp,
                color = TextPrimary
            )

            Text(
                text = ">",
                fontSize = 16.sp,
                color = ElectricIndigo
            )
        }
    }
}