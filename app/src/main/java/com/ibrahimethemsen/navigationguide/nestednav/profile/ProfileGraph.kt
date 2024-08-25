package com.ibrahimethemsen.navigationguide.nestednav.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.ibrahimethemsen.navigationguide.bottomsheet.AppBottomSheet
import kotlinx.serialization.Serializable

@Serializable
object ProfileGraph

@Serializable
object ProfileStart

@Serializable
data class ProfileDetail(
    val user: String
)

fun NavGraphBuilder.profile(navController: NavController) {
    navigation<ProfileGraph>(ProfileStart) {
        composable<ProfileStart> {
            var textField by remember {
                mutableStateOf("")
            }
            var showBottomSheet by remember { mutableStateOf(false) }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(value = textField, onValueChange = { textField = it })
                Button(onClick = {
                    navController.navigate(ProfileDetail(textField))
                }) {
                    Text(text = "Gonder")
                }
                Button(onClick = {
                    showBottomSheet = true
                }) {
                    Text(text = "Show Bottom Sheet")
                }
            }
            AppBottomSheet(showBottomSheet) { showBottomSheet = it }
        }
        composable<ProfileDetail> { backStack ->
            val userArg = backStack.toRoute<ProfileDetail>().user
            Text(text = "Detail Screen Arg $userArg")
        }
    }
}

