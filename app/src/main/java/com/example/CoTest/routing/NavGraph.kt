package com.example.CoTest.routing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.CoTest.R
import com.example.CoTest.presentation.detailUser.DetailUser
import com.example.CoTest.presentation.home.HomeScreen
import com.example.CoTest.tools.navigation.currentRoute

/**
 *create the navHost composable from the declared Screen
 */
@Composable
fun Navigation(
    navController: NavHostController, modifier: Modifier
) {
    NavHost(
        navController,
        startDestination = Screen.HomeNav.route,
        modifier.clearAndSetSemantics { contentDescription = "HomeNav" }) {

        composable(Screen.HomeNav.route) {
            HomeScreen(
                navController = navController
            )

        }

        composable(
            Screen.Detail.route.plus(Screen.Detail.objectPath),
            arguments = listOf(navArgument(Screen.Detail.objectName) {
                type = NavType.StringType
            })
        ) {
            label = stringResource(R.string.detail)
            val userId = it.arguments?.getString(Screen.Detail.objectName)
            if (userId != null) {
                DetailUser(
                    userId
                )
            }

        }
    }

}

@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        Screen.HomeNav.route -> stringResource(id = R.string.app_title)
        else -> {
            ""
        }
    }
}