package com.example.CoTest.presentation.mainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.CoTest.R
import com.example.CoTest.res.progressBar.CircularIndeterminateProgressBar
import com.example.CoTest.res.theme.Pink40
import com.example.CoTest.routing.Navigation
import com.example.CoTest.routing.Screen
import com.example.CoTest.routing.navigationTitle
import com.example.CoTest.tools.component.appbar.AppBarWithArrow
import com.example.CoTest.tools.component.appbar.HomeAppBar
import com.example.CoTest.tools.navigation.currentRoute
import com.example.CoTest.tools.component.appbar.SearchBar
import com.example.CoTest.tools.networkconnection.ConnectionState
import com.example.CoTest.tools.networkconnection.connectivityState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val isAppBarVisible = remember { mutableStateOf(true) }
    val searchProgressBar = remember { mutableStateOf(false) }
    val mainViewModel: MainViewModel = koinViewModel()

    // internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    // genre api call for first time
    LaunchedEffect(key1 = 0) {
        //    mainViewModel.genreList()
    }


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
                FloatingActionButton(
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
        }

    }
}
