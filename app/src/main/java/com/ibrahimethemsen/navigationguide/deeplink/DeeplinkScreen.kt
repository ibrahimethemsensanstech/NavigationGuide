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
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink


//  Deeplink -> adb shell am start -W -a android.intent.action.VIEW -d "team://sanstech/deeplink/demiroren/androidteam"
fun NavGraphBuilder.deeplinkRoute() {
    composable(
        route = "deeplink",
        deepLinks = listOf(navDeepLink {
            uriPattern = "team://sanstech/deeplink/{company}/{name}"
            action = Intent.ACTION_VIEW
        }),
        arguments = listOf(
            navArgument("company") {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument("name") {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) { navBackStackEntry ->
        val company = navBackStackEntry.arguments?.getString("company")
        val name = navBackStackEntry.arguments?.getString("name")

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Deeplink Screen")
            Text(text = "Company : $company", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "Name : $name", fontSize = 18.sp)
        }
    }
}