package com.ibrahimethemsen.navigationguide

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ibrahimethemsen.navigationguide.animation.animationGraph
import com.ibrahimethemsen.navigationguide.deeplink.deeplinkRoute
import com.ibrahimethemsen.navigationguide.deeplink.deeplinkTypeSafeRoute
import com.ibrahimethemsen.navigationguide.dialog.DialogNav
import com.ibrahimethemsen.navigationguide.dialog.FullDialog
import com.ibrahimethemsen.navigationguide.dialog.dialogDestination
import com.ibrahimethemsen.navigationguide.dialog.fullDialog
import com.ibrahimethemsen.navigationguide.ext.replacePackage
import com.ibrahimethemsen.navigationguide.nestednav.NestedNav
import com.ibrahimethemsen.navigationguide.nestednavhost.NestedNavHost
import com.ibrahimethemsen.navigationguide.playground.PlaygroundNavHost
import com.ibrahimethemsen.navigationguide.sharednav.SharedNavHost
import com.ibrahimethemsen.navigationguide.sharednav.SharedTypeSafeNavHostScreen
import com.ibrahimethemsen.navigationguide.ui.theme.NavigationGuideTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavigationGuideTheme {
                TrackerColumn(title = "App", navController = navController) {
                    NavHost(navController = navController, startDestination = AppHome) {
                        composable<AppHome> {
                            NavigateColumn(navController)
                        }
                        composable<SharedNavHost> {
                            SharedNavHost()
                        }
                        composable<SharedTypeSafeNavHost> {
                            SharedTypeSafeNavHostScreen()
                        }
                        composable<NestedNav> {
                            NestedNav()
                        }
                        composable<NestedNavHost> {
                            NestedNavHost()
                        }
                        composable<Playground> {
                            PlaygroundNavHost()
                        }
                        dialogDestination(navController)
                        fullDialog()
                        deeplinkTypeSafeRoute()
                        deeplinkRoute()
                        animationGraph(navController)

                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("RestrictedApi")
@Composable
fun NavHostBackStackTracker(
    title: String,
    navController: NavController
) {
    val backStack = navController.currentBackStack.collectAsStateWithLifecycle()
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = "$title: ", color = Color.White)
        backStack.value.forEach {
            if (!it.destination.route.isNullOrBlank()) {
                Text(
                    text = "-> ${
                        it.destination.route!!.replacePackage()
                    }",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun TrackerColumn(
    title: String,
    navController: NavController,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column {
        NavHostBackStackTracker(title = title, navController = navController)
        content()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NavigateColumn(navController : NavController) {
    FlowRow(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp,Alignment.CenterVertically),
        horizontalArrangement = Arrangement.spacedBy(4.dp,Alignment.CenterHorizontally)
    ) {
        Button(onClick = {
            navController.navigate(NestedNavHost)
        }) {
            Text(text = "Nested NavHost")
        }
        Button(onClick = {
            navController.navigate(NestedNav)
        }) {
            Text(text = "Nested Nav")
        }
        Button(onClick = {
            navController.navigate(SharedNavHost)
        }) {
            Text(text = "Shared Navigation")
        }
        Button(onClick = {
            navController.navigate(SharedTypeSafeNavHost)
        }) {
            Text(text = "Shared Type Safe Navigation")
        }
        Button(onClick = {
            navController.navigate(DialogNav)
        }) {
            Text(text = "Dialog Destination")
        }
        Button(onClick = {
            navController.navigate(FullDialog)
        }) {
            Text(text = "Full Dialog")
        }
        Button(onClick = {
            navController.navigate(FullDialog)
        }) {
            Text(text = "Full Dialog")
        }
        Button(onClick = {
            navController.navigate(AnimateNavigation)
        }) {
            Text(text = "Animate Navigation")
        }
        Button(onClick = {
            navController.navigate(Playground)
        }) {
            Text(text = "Navigation Playground")
        }
    }
}

@Serializable
object AppHome

@Serializable
object NestedNavHost

@Serializable
object SharedNavHost

@Serializable
object SharedTypeSafeNavHost

@Serializable
object NestedNav

@Serializable
object AnimateNavigation

@Serializable
object Playground


