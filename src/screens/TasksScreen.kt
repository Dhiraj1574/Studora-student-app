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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studora.app.ui.theme.*

data class TaskItem(
    val title: String,
    val due: String,
    val time: String,
    val priority: String
)

@Composable
fun TasksScreen(modifier: Modifier = Modifier) {
    val showDialog = remember { mutableStateOf(false) }

    val tasks = remember {
        mutableStateOf(
            listOf(
                TaskItem("Kotlin Submission", "Today", "7:00 AM", "High Priority"),
                TaskItem("Android Submission", "Today", "8:00 AM", "High Priority"),
                TaskItem("UI Assignment Submission", "Today", "10:00 AM", "High Priority"),
                TaskItem("Prepare DBMS Viva Questions", "Today", "2:00 PM", "Medium Priority"),
                TaskItem("Complete Android Practical", "Tomorrow", "11:00 AM", "Low Priority")
            )
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .statusBarsPadding()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Text("Tasks", fontSize = 30.sp, color = TextPrimary)

            Text(
                text = "Manage assignments and reminders.",
                fontSize = 15.sp,
                color = ElectricIndigo,
                modifier = Modifier.padding(top = 6.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TaskSummaryCard(tasks.value.size.toString(), "Total Tasks")
                TaskSummaryCard(
                    tasks.value.count { it.priority == "High Priority" }.toString(),
                    "High Priority"
                )
            }

            Text(
                text = "Today’s Tasks",
                fontSize = 20.sp,
                color = TextPrimary,
                modifier = Modifier.padding(top = 28.dp)
            )

            tasks.value
                .sortedWith(
                    compareBy<TaskItem> {
                        when (it.priority) {
                            "High Priority" -> 1
                            "Medium Priority" -> 2
                            else -> 3
                        }
                    }.thenBy {
                        convertTaskTimeToMinutes(it.time)
                    }
                )
                .forEach { task ->
                    TaskCard(task.title, task.due, task.time, task.priority)
                }

            Spacer(modifier = Modifier.height(90.dp))
        }

        FloatingActionButton(
            onClick = { showDialog.value = true },
            containerColor = ElectricIndigo,
            contentColor = TextPrimary,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 12.dp)
        ) {
            Text("+", fontSize = 28.sp)
        }
    }

    if (showDialog.value) {
        AddTaskDialog(
            onDismiss = { showDialog.value = false },
            onAddTask = { title, due, time, priority ->
                tasks.value = tasks.value + TaskItem(title, due, time, priority)
                showDialog.value = false
            }
        )
    }
}

@Composable
fun AddTaskDialog(
    onDismiss: () -> Unit,
    onAddTask: (String, String, String, String) -> Unit
) {
    val title = remember { mutableStateOf("") }
    val due = remember { mutableStateOf("") }
    val time = remember { mutableStateOf("") }
    val selectedPriority = remember { mutableStateOf("Medium Priority") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    if (title.value.isNotBlank()) {
                        onAddTask(
                            title.value,
                            if (due.value.isBlank()) "No Due Date" else due.value,
                            if (time.value.isBlank()) "No Time" else time.value,
                            selectedPriority.value
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
            Text("Add New Task", color = TextPrimary)
        },
        text = {
            Column {
                OutlinedTextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    label = { Text("Task Title") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = due.value,
                    onValueChange = { due.value = it },
                    label = { Text("Due Date") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = time.value,
                    onValueChange = { time.value = it },
                    label = { Text("Time, example: 10:00 AM") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Priority", color = TextPrimary, fontSize = 14.sp)

                PriorityOption("High Priority", selectedPriority.value == "High Priority") {
                    selectedPriority.value = "High Priority"
                }

                PriorityOption("Medium Priority", selectedPriority.value == "Medium Priority") {
                    selectedPriority.value = "Medium Priority"
                }

                PriorityOption("Low Priority", selectedPriority.value == "Low Priority") {
                    selectedPriority.value = "Low Priority"
                }
            }
        },
        containerColor = CardDark
    )
}

@Composable
fun PriorityOption(label: String, selected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) ElectricIndigo else BackgroundDark
        )
    ) {
        Text(
            text = label,
            color = TextPrimary,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
        )
    }
}

@Composable
fun TaskSummaryCard(value: String, label: String) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(95.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(value, fontSize = 28.sp, color = ElectricIndigo)
            Text(label, fontSize = 14.sp, color = TextSecondary)
        }
    }
}

@Composable
fun TaskCard(title: String, due: String, time: String, priority: String) {
    val priorityColor = when (priority) {
        "High Priority" -> ErrorRed
        "Medium Priority" -> WarningOrange
        else -> SuccessGreen
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .border(
                width = 1.5.dp,
                color = priorityColor,
                shape = RoundedCornerShape(20.dp)
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(title, color = TextPrimary, fontSize = 17.sp)

            Text(
                text = "$due • $time • $priority",
                color = priorityColor,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}

fun convertTaskTimeToMinutes(time: String): Int {
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