package com.ibrahimethemsen.navigationguide.nestednav.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
object ListGraph

@Serializable
object ListStart

@Serializable
data class ListDetail(
    val itemId : Int,
)

fun NavGraphBuilder.list(navController: NavController) {
    navigation<ListGraph>(ListStart) {
        composable<ListStart> {
            LazyColumn {
                items((1..100).toList()) {
                    Text(modifier = Modifier
                        .clickable { navController.navigate(ListDetail(it)) }
                        .fillMaxWidth()
                        .padding(20.dp), text = "Text $it")
                }
            }
        }
        composable<ListDetail> {
            val id = it.toRoute<ListDetail>().itemId
            Text(text = "Detail Screen $id")
        }
    }
}

