package com.ibrahimethemsen.navigationguide.nestednav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ibrahimethemsen.navigationguide.TrackerColumn
import com.ibrahimethemsen.navigationguide.ext.createRoutePattern
import com.ibrahimethemsen.navigationguide.ext.replacePackage
import com.ibrahimethemsen.navigationguide.nestednav.home.HomeGraph
import com.ibrahimethemsen.navigationguide.nestednav.home.home
import com.ibrahimethemsen.navigationguide.nestednav.list.ListGraph
import com.ibrahimethemsen.navigationguide.nestednav.list.list
import com.ibrahimethemsen.navigationguide.nestednav.profile.ProfileGraph
import com.ibrahimethemsen.navigationguide.nestednav.profile.profile
import com.ibrahimethemsen.navigationguide.nestednavhost.BottomNavigationItem

@Composable
fun NestedNav() {
    val nestedNavController = rememberNavController()
    val navBackStackEntry by nestedNavController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                nestedItems.forEach { item ->
                    val isSelected = item.title.lowercase() ==
                            navBackStackEntry?.destination?.route
                    NavigationBarItem(
                        selected = isSelected,
                        label = {
                            Text(text = item.title.replacePackage())
                        },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        onClick = {
                            nestedNavController.navigate(item.title.lowercase()) {
                                popUpTo(nestedNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        TrackerColumn(title = "Nested", navController = nestedNavController) {
            NavHost(
                navController = nestedNavController,
                startDestination = HomeGraph,
            ) {
                home(nestedNavController)
                list(nestedNavController)
                profile(nestedNavController)
            }
        }
    }
}

val nestedItems = listOf(
    BottomNavigationItem(
        title = createRoutePattern<HomeGraph>(),
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),
    BottomNavigationItem(
        title = createRoutePattern<ListGraph>(),
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Outlined.List,
    ),
    BottomNavigationItem(
        title = createRoutePattern<ProfileGraph>(),
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
    ),
)


