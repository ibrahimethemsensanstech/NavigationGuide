package com.ibrahimethemsen.navigationguide.deeplink

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class ScreenDeeplinkTypeSafe(
    val company: String,
    val name: String,
)

//  Deeplink -> adb shell am start -W -a android.intent.action.VIEW -d "team://sanstech/deeplinksafe/demiroren/androidteam"
fun NavGraphBuilder.deeplinkTypeSafeRoute() {
    composable<ScreenDeeplinkTypeSafe>(
        deepLinks = listOf(navDeepLink {
            uriPattern = "team://sanstech/deeplinksafe/{company}/{name}"
            action = Intent.ACTION_VIEW
        })
    ) {
        val company = it.toRoute<ScreenDeeplinkTypeSafe>().company
        val name = it.toRoute<ScreenDeeplinkTypeSafe>().name
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Type Safe Deeplink Screen")
            Text(
                text = "Type Safe Company : $company",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Type Safe Name : $name", fontSize = 18.sp)
        }
    }
}