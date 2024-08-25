package com.ibrahimethemsen.navigationguide.playground

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ibrahimethemsen.navigationguide.TrackerColumn
import kotlinx.serialization.Serializable

@Serializable
object PlaygroundStart

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlaygroundNavHost() {
    val playgroundNavController = rememberNavController()
    var indexScreen by remember { mutableIntStateOf(1) }
    TrackerColumn(
        title = "Playground",
        navController = playgroundNavController
    ) {
        NavHost(
            modifier = Modifier.weight(1f),
            navController = playgroundNavController,
            startDestination = PlaygroundStart
        ) {
            composable<PlaygroundStart> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Playground Start")
                }
            }
            (1..100).forEach {
                composable("playground$it") {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Playground Index Screen ${it.destination.route}")
                    }
                }
            }
        }
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Button(onClick = {
                indexScreen += 1
                playgroundNavController.navigate("playground${indexScreen}")
            }) {
                Text(text = "Add")
            }
            Button(onClick = { playgroundNavController.navigateUp() }) {
                Text(text = "NavigateUp")
            }
            Button(onClick = { playgroundNavController.popBackStack() }) {
                Text(text = "PopBackStack")
            }
            Button(onClick = {
                playgroundNavController.navigate(PlaygroundStart) {
                    popUpTo(playgroundNavController.graph.startDestinationId) { inclusive = true }
                }
            }) {
                Text(text = "PopUpTo")
            }
        }
    }
}