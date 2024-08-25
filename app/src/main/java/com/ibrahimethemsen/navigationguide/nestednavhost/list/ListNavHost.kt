package com.ibrahimethemsen.navigationguide.nestednavhost.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ibrahimethemsen.navigationguide.TrackerColumn


@Composable
fun ListNavHost() {
    val listNavController = rememberNavController()
    TrackerColumn(title = "List", navController = listNavController) {
        NavHost(navController = listNavController, startDestination = "list") {
            composable("list") {
                LazyColumn {
                    items((1..100).toList()) {
                        Text(modifier = Modifier
                            .clickable { listNavController.navigate("detail") }
                            .fillMaxWidth()
                            .padding(20.dp), text = "Text $it")
                    }
                }
            }
            composable("detail") {
                Text(text = "Detail Screen")
            }
        }
    }
}
