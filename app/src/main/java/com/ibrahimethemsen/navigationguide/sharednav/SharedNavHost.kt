package com.ibrahimethemsen.navigationguide.sharednav

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ibrahimethemsen.navigationguide.TrackerColumn

sealed class SharedScreens(val route: String) {
    data object Home : SharedScreens("home")
    data object Detail : SharedScreens("detail")
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedNavHost() {
    SharedTransitionLayout {
        val navController = rememberNavController()
        TrackerColumn(title = "Shared", navController = navController) {
            NavHost(navController = navController, startDestination = SharedScreens.Home.route) {
                composable(SharedScreens.Home.route) {
                    HomeScreen(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable
                    ) {
                        navController.navigate("${SharedScreens.Detail.route}/$it")
                    }
                }
                composable(
                    route = "${SharedScreens.Detail.route}/{item}",
                    arguments = listOf(navArgument("item") { type = NavType.IntType })
                ) { navBackStackEntry ->
                    val id = navBackStackEntry.arguments?.getInt("item")
                    val techCompany = techCompanyList[id!!]
                    DetailScreen(
                        id = id,
                        techItem = techCompany,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable,
                        popUp = {
                            navController.popBackStack()
                        }
                    ) {
                        navController.navigate(SharedScreens.Home.route)
                    }
                }
            }
        }
    }
}


