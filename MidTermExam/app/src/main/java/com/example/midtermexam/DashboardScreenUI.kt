package com.example.midtermexam

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

object DashboardScreenUI {
    @Composable
    fun setupLayout(modifier: Modifier, navController: NavController){
        DashboardInit(modifier, navController)
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DashboardInit(
        modifier: Modifier,
        navController: NavController,

    ) {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Student Roster") }) },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate("addstudent_screen")
                    }
                ) {
                    // The icon inside the button
                    Icon(Icons.Filled.Add, contentDescription = "Add Item")
                }
            },
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0x00000000),
                                Color.Transparent
                            )
                        )
                    )
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    // Apply padding from the Scaffold
                    .padding(paddingValues),
                // Optional: Add padding inside the list itself
                contentPadding = PaddingValues(16.dp),
                // Optional: Spacing between items
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Define your two elements/items here
                item {
                    ListItem(
                        headlineContent = { Text("Student ID 1") },
                        supportingContent = { Text("Student Name 1") },
                    )
                }
                item {
                    ListItem(
                        headlineContent = { Text("Student ID 2") },
                        supportingContent = { Text("Student Name 2") },
                    )
                }

                // If you wanted to test scrolling, you could add many more items like this:
                /*
                items(100) { index ->
                     ListItem(title = "Test Item ${index + 3}", subtitle = "Extra long list for scrolling.")
                }
                */
                }
            }
        }
    }
