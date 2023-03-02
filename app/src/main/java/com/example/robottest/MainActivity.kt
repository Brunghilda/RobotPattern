package com.example.robottest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.robottest.ui.theme.RobotTestTheme

class MainActivity : ComponentActivity() {

    private companion object {
        const val ROUTE_LOGIN = "login"
        const val ROUTE_HOME = "home"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RobotTestTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = ROUTE_LOGIN) {
                        composable(ROUTE_LOGIN) {
                            LoginRegistration(goToHome = {
                                navController.navigate(ROUTE_HOME)
                            })
                        }
                        composable(ROUTE_HOME) { Home() }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RobotTestTheme {
    }
}