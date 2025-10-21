package com.example.midtermexam

import Item
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import studentsViewModel


object AddStudentScreenUI {
    @Composable
    fun setupLayout(modifier: Modifier, navController: NavHostController) {
        addStudentInit(modifier, navController)
    }

    @Composable
    fun addStudentInit(
        modifier: Modifier,
        navController: NavHostController,
        viewModel: studentsViewModel = viewModel()
        ) {
        val items by viewModel.dataList.collectAsState()

        var studentid by remember { mutableStateOf("") }
        var studentname by remember { mutableStateOf("") }

        var lackOfData = studentid.isEmpty() || studentname.isEmpty()

        Row {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color(0xFF2193b0),
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .wrapContentHeight()
                        .background(
                            color = Color.White.copy(alpha = 0.95f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color.White.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(24.dp)
                ) {
                    Column {
                        Button(
                            onClick = {
                                    navController.navigate("dashboard_screen")
                            },
                            modifier = Modifier
                                .height(50.dp),
                            shape = RoundedCornerShape(36.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF0011),
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Cancel",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Text(
                            text = "Add Student Page",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = Color(0xFF2193b0),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        OutlinedTextField(
                            value = studentid,
                            onValueChange = { studentid = it },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        OutlinedTextField(
                            value = studentname,
                            onValueChange = { studentname = it },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        // 4. Displaying the Result (Error Message)
                        if (lackOfData) {
                            Text(
                                text = "Lack of Data",
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = {
                                if (!lackOfData) {
                                    { onAddItem() }
                                    navController.navigate("dashboard_screen")
                                }
                            },
                            enabled = !lackOfData,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2193b0),
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Add Student",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
            }
        }
        fun onAddItem() {
            val newId = studentid.toIntOrNull()
            if (newId != null && studentname.isNotBlank()) {
                val newItem = Item(newId, studentname)
                viewModel.addItem(newItem)
                studentid = "" // Reset ID field
                studentname = "" // Reset Name field
            }
        }
    }
}