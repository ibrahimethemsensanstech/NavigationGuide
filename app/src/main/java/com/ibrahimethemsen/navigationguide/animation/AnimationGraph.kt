package com.ibrahimethemsen.navigationguide.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ibrahimethemsen.navigationguide.AnimateNavigation
import kotlinx.serialization.Serializable

object Destination {
    @Serializable
    object AnimationStart

    @Serializable
    object AnimationTwoScreen

    @Serializable
    object AnimationThreeScreen
}


fun NavGraphBuilder.animationGraph(
    navController: NavController
) {
    navigation<AnimateNavigation>(Destination.AnimationStart) {
        composable<Destination.AnimationStart>(enterTransition = {
            return@composable fadeIn(tween(1000))
        }, exitTransition = {
            return@composable fadeOut(tween(700))
        }, popEnterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(700)
            )
        }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { navController.navigate(Destination.AnimationTwoScreen) },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Animation Start Screen")
            }
        }
        composable<Destination.AnimationTwoScreen>(
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { navController.navigate(Destination.AnimationThreeScreen) },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Animation Two Screen")
            }
        }
        composable<Destination.AnimationThreeScreen>(enterTransition = {
            return@composable expandIn(tween(700))
        }, exitTransition = {
            return@composable shrinkOut(tween(700))
        }, popExitTransition = { return@composable shrinkOut() }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { navController.navigate(Destination.AnimationStart) },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Animation Three Screen")
            }
        }
    }
}