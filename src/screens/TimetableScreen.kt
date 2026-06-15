package com.studora.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studora.app.ui.theme.*

data class TimetableClassItem(
    val time: String,
    val subject: String,
    val room: String
)

@Composable
fun TimetableScreen(
    modifier: Modifier = Modifier
) {
    val selectedDay = remember { mutableStateOf("Mon") }
    val showDialog = remember { mutableStateOf(false) }

    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    val timetable = remember {
        mutableStateOf(
            mutableMapOf(
                "Mon" to mutableListOf(
                    TimetableClassItem("09:00 AM", "Data Structures", "Room 204"),
                    TimetableClassItem("11:00 AM", "Operating Systems", "Lab 3"),
                    TimetableClassItem("02:00 PM", "DBMS", "Room 102"),
                    TimetableClassItem("04:00 PM", "Project Work", "Lab 4")
                ),
                "Tue" to mutableListOf(
                    TimetableClassItem("10:00 AM", "Computer Networks", "Room 301")
                ),
                "Wed" to mutableListOf(),
                "Thu" to mutableListOf(),
                "Fri" to mutableListOf(),
                "Sat" to mutableListOf(),
                "Sun" to mutableListOf()
            )
        )
    }

    val classes = mutableListOf<TimetableClassItem>()

    timetable.value[selectedDay.value]?.forEach { item ->
        classes.add(item)
    }

    classes.sortWith { first, second ->
        classTimeToMinutes(first.time) - classTimeToMinutes(second.time)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .statusBarsPadding()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Timetable",
                fontSize = 30.sp,
                color = TextPrimary
            )

            Text(
                text = "Manage your classes smartly.",
                fontSize = 15.sp,
                color = ElectricIndigo,
                modifier = Modifier.padding(top = 6.dp)
            )

            Row(
                modifier = Modifier.padding(top = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                days.forEach { day ->
                    DayChip(
                        day = day,
                        selected = selectedDay.value == day,
                        onClick = {
                            selectedDay.value = day
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (classes.isEmpty()) {
                Text(
                    text = "No classes scheduled for ${selectedDay.value}.",
                    color = TextSecondary,
                    fontSize = 15.sp
                )
            } else {
                classes.forEachIndexed { index, item ->
                    TimetableClassCard(
                        time = item.time,
                        subject = item.subject,
                        room = item.room,
                        isLive = index == 0
                    )
                }
            }

            Spacer(modifier = Modifier.height(110.dp))
        }

        FloatingActionButton(
            onClick = {
                showDialog.value = true
            },
            containerColor = ElectricIndigo,
            contentColor = TextPrimary,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 12.dp)
        ) {
            Text(
                text = "+",
                fontSize = 28.sp
            )
        }
    }

    if (showDialog.value) {
        AddClassDialog(
            day = selectedDay.value,
            onDismiss = {
                showDialog.value = false
            },
            onAddClass = { time, subject, room ->

                val updatedMap = timetable.value.toMutableMap()

                val updatedList =
                    updatedMap[selectedDay.value]?.toMutableList()
                        ?: mutableListOf()

                updatedList.add(
                    TimetableClassItem(
                        time = if (time.isBlank()) "Time not set" else time,
                        subject = subject,
                        room = if (room.isBlank()) "Room not set" else room
                    )
                )

                updatedMap[selectedDay.value] = updatedList
                timetable.value = updatedMap

                showDialog.value = false
            }
        )
    }
}

fun classTimeToMinutes(time: String): Int {
    return try {
        val parts = time.trim().split(" ")
        val hourMinute = parts[0].split(":")
        var hour = hourMinute[0].toInt()
        val minute = hourMinute[1].toInt()
        val amPm = parts[1].uppercase()

        if (amPm == "PM" && hour != 12) hour += 12
        if (amPm == "AM" && hour == 12) hour = 0

        hour * 60 + minute
    } catch (e: Exception) {
        9999
    }
}

@Composable
fun AddClassDialog(
    day: String,
    onDismiss: () -> Unit,
    onAddClass: (String, String, String) -> Unit
) {
    val time = remember { mutableStateOf("") }
    val subject = remember { mutableStateOf("") }
    val room = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    if (subject.value.isNotBlank()) {
                        onAddClass(
                            time.value,
                            subject.value,
                            room.value
                        )
                    }
                }
            ) {
                Text("Add", color = ElectricIndigo)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = TextSecondary)
            }
        },
        title = {
            Text("Add Class for $day", color = TextPrimary)
        },
        text = {
            Column {
                OutlinedTextField(
                    value = time.value,
                    onValueChange = { time.value = it },
                    label = { Text("Time, example: 10:00 AM") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = subject.value,
                    onValueChange = { subject.value = it },
                    label = { Text("Subject") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = room.value,
                    onValueChange = { room.value = it },
                    label = { Text("Room") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        containerColor = CardDark
    )
}

@Composable
fun DayChip(
    day: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) ElectricIndigo else CardDark
        )
    ) {
        Text(
            text = day,
            color = TextPrimary,
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
        )
    }
}

@Composable
fun TimetableClassCard(
    time: String,
    subject: String,
    room: String,
    isLive: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .border(
                width = if (isLive) 1.5.dp else 0.dp,
                color = if (isLive) ErrorRed else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = time,
                    color = ElectricIndigo,
                    fontSize = 13.sp
                )

                Text(
                    text = if (isLive) "LIVE" else "Upcoming",
                    color = if (isLive) ErrorRed else TextSecondary,
                    fontSize = 12.sp
                )
            }

            Text(
                text = subject,
                color = TextPrimary,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = room,
                color = TextSecondary,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}