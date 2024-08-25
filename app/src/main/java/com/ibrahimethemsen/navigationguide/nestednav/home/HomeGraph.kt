package com.ibrahimethemsen.navigationguide.nestednav.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

fun NavGraphBuilder.home(navController: NavController) {
    navigation<HomeGraph>(Home1) {
        composable<Home1> {
            Column {
                Text(text = "Home1")
                Button(onClick = { navController.navigate(Home2) }) {
                    Text(text = "Navigate To Home2")
                }
            }
        }
        composable<Home2> {
            Column {
                Text(text = "Home2")
                Button(onClick = { navController.navigate(Home3) }) {
                    Text(text = "Navigate To Home3")
                }
            }
        }
        composable<Home3> {
            Column {
                Text(text = "Home3")
                Button(onClick = { navController.navigate(Home4) }) {
                    Text(text = "Navigate To Home4")
                }
            }
        }
        composable<Home4> {
            Column {
                Text(text = "Home4")
                Button(onClick = { navController.navigate(Home5) }) {
                    Text(text = "Navigate To Home5")
                }
            }
        }
        composable<Home5> {
            Column {
                Text(text = "Home5")
                Button(onClick = { navController.navigate(Home1) }) {
                    Text(text = "Navigate To Home1")
                }
            }
        }
    }
}

@Serializable
object HomeGraph

@Serializable
object Home1

@Serializable
object Home2

@Serializable
object Home3

@Serializable
object Home4

@Serializable
object Home5