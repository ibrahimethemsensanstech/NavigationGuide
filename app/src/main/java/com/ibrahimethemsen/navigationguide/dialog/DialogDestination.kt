package com.ibrahimethemsen.navigationguide.dialog

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import androidx.navigation.navDeepLink
import com.ibrahimethemsen.navigationguide.SharedNavHost
import kotlinx.serialization.Serializable

@Serializable
data object DialogNav

fun NavGraphBuilder.dialogDestination(navController: NavController) {
    dialog<DialogNav> {
        Column(
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color.DarkGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Dialog Navigation", color = Color.White)
            Button(onClick = {
                navController.navigate(SharedNavHost)
            }) {
                Text(text = "Navigate To Shared")
            }
        }
    }
}

@Serializable
data object FullDialog

fun NavGraphBuilder.fullDialog() {
    dialog<FullDialog>(
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false),
        deepLinks = listOf(navDeepLink {
            // Deeplink -> adb shell am start -W -a android.intent.action.VIEW -d "team://sanstech/fulldialog"
            uriPattern = "team://sanstech/fulldialog"
            action = Intent.ACTION_VIEW
        })
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Full Dialog Text")
        }
    }
}
