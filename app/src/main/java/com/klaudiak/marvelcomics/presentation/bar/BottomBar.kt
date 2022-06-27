package com.klaudiak.marvelcomics.presentation.bar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.klaudiak.marvelcomics.ui.theme.White40


@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search
    )
    NavigationBar(
        modifier  = Modifier.fillMaxWidth(),
        containerColor = Color.White,
        contentColor = Color.White
    ) {
        items.forEach{ item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = null) },
               colors = NavigationBarItemDefaults.colors(selectedIconColor = Color.Red, indicatorColor = White40) ,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })

        }
    }

}
