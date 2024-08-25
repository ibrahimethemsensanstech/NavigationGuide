package com.ibrahimethemsen.navigationguide.nestednavhost.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ibrahimethemsen.navigationguide.TrackerColumn
import com.ibrahimethemsen.navigationguide.bottomsheet.AppBottomSheet

@Composable
fun ProfileNavHost() {
    val profileNavController = rememberNavController()

    TrackerColumn(title = "Profile", navController = profileNavController) {
        NavHost(navController = profileNavController, startDestination = "profile") {
            composable("profile") {
                var showBottomSheet by remember { mutableStateOf(false) }
                var textField by remember {
                    mutableStateOf("")
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(value = textField, onValueChange = { textField = it })
                    Button(onClick = {
                        profileNavController.navigate("profileDetail/$textField")
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
            composable(
                route = "profileDetail",
                arguments = listOf(
                    navArgument("user") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )
            ) { backStack ->
                val detailArg = backStack.arguments?.getString("user")
                Text(text = "Detail Screen Arg $detailArg")
            }
        }
    }
}
