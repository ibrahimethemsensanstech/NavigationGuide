package com.ibrahimethemsen.navigationguide.sharednav

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ibrahimethemsen.navigationguide.TrackerColumn
import kotlinx.serialization.Serializable

@Serializable
object TypeSafeHome

@Serializable
data class TypeSafeDetail(
    val itemId: Int,
)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTypeSafeNavHostScreen() {
    SharedTransitionLayout {
        val navController = rememberNavController()
        TrackerColumn(title = "SharedSafe", navController = navController) {
            NavHost(navController = navController, startDestination = TypeSafeHome) {
                composable<TypeSafeHome> {
                    HomeScreen(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable
                    ) {
                        navController.navigate(TypeSafeDetail(it))
                    }
                }
                composable<TypeSafeDetail> { navBackStackEntry ->
                    val id = navBackStackEntry.toRoute<TypeSafeDetail>().itemId
                    val techCompany = techCompanyList[id]
                    DetailScreen(
                        id = id,
                        techItem = techCompany,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable,
                        popUp = {
                            navController.popBackStack()
                        }
                    ) {
                        navController.navigate(TypeSafeHome)
                    }
                }
            }
        }
    }
}
