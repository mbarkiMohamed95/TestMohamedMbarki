package com.example.CoTest.presentation.mainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.CoTest.R
import com.example.CoTest.res.theme.Pink40
import com.example.CoTest.routing.Navigation
import com.example.CoTest.routing.Screen
import com.example.CoTest.routing.navigationTitle
import com.example.CoTest.tools.component.SearchUI
import com.example.CoTest.tools.component.appbar.AppBarWithArrow
import com.example.CoTest.tools.component.appbar.HomeAppBar
import com.example.CoTest.tools.component.appbar.SearchBar
import com.example.CoTest.tools.navigation.currentRoute
import com.example.CoTest.tools.networkconnection.ConnectionState
import com.example.CoTest.tools.networkconnection.connectivityState
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val isAppBarVisible = remember { mutableStateOf(true) }
    val mainViewModel: MainViewModel = koinViewModel()
    // internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

     Scaffold(scaffoldState = scaffoldState, topBar = {
        when (currentRoute(navController)) {
            Screen.HomeNav.route -> {
                if (isAppBarVisible.value) {
                    val appTitle: String = stringResource(R.string.app_title)
                    HomeAppBar(title = appTitle, openFilters = {
                        isAppBarVisible.value = false
                    })
                } else {
                    SearchBar(isAppBarVisible, mainViewModel)
                }
            }

            else -> {
                AppBarWithArrow(navigationTitle(navController)) {
                    navController.popBackStack()
                }
            }
        }
    }, floatingActionButton = {
        when (currentRoute(navController)) {
            Screen.HomeNav.route -> {
                FloatingActionButton(modifier = Modifier.testTag("searchAction"),
                    onClick = {
                        isAppBarVisible.value = false
                    }, backgroundColor = Pink40
                ) {
                    Icon(Icons.Filled.Search, "", tint = Color.White)
                }
            }
        }
    }, snackbarHost = {
        if (isConnected.not()) {
            Snackbar(
                action = {}, modifier = Modifier.padding(8.dp)
            ) {
                Text(text = stringResource(R.string.there_is_no_internet))
            }
        }
    }) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Navigation(navController, Modifier.padding(it))
            Column {
                if (isAppBarVisible.value.not()) {
                    SearchUI(navController,mainViewModel.searchData.value ) {
                        isAppBarVisible.value = true
                    }
                }
            }
        }

    }
}
