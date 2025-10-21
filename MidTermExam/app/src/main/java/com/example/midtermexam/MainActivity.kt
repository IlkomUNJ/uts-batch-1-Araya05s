package com.example.midtermexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.midtermexam.ui.theme.MidTermExamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MidTermExamTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier) {
    // 1. Create and remember the NavController
    val navController = rememberNavController()

    // 2. Define the NavHost and the navigation graph
    NavHost(
        navController = navController,
        startDestination = "login_screen" // Define the initial screen
    ) {
        // Define the 'home_screen' destination
        composable("login_screen") {
            LoginScreenUI.setupLayout(modifier,navController = navController)
        }

        // Define the 'detail_screen' destination
        composable("dashboard_screen") {
            DashboardScreenUI.setupLayout(modifier,navController = navController)
        }

        composable("addstudent_screen") {
            AddStudentScreenUI.setupLayout(modifier,navController = navController)
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MidTermExamTheme {
//        Greeting("Android")
//    }
//}