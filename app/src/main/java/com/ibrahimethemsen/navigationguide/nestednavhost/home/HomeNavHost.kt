package com.ibrahimethemsen.navigationguide.nestednavhost.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ibrahimethemsen.navigationguide.TrackerColumn


@Composable
fun HomeNavHost() {
    val homeNavController = rememberNavController()
    TrackerColumn(title = "Home", navController = homeNavController) {
        NavHost(navController = homeNavController, startDestination = "home1") {
            (1..10).forEach { destination ->
                composable("home$destination") {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { homeNavController.navigate("home${destination + 1}") },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Home Screen $destination")
                    }
                }
            }
        }
    }
}