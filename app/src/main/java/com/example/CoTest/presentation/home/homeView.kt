package com.example.CoTest.presentation.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.domain.loadUseCase.model.UserModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.CoTest.res.progressBar.CircularIndeterminateProgressBar
import com.example.CoTest.res.theme.DefaultBackgroundColor
import com.example.CoTest.routing.Screen
import com.example.CoTest.tools.component.ExitAlertDialog
import com.example.CoTest.tools.navigation.currentRoute
import org.koin.androidx.compose.koinViewModel
import com.example.CoTest.tools.component.items
import com.example.CoTest.tools.component.pagingLoadingState
import com.example.CoTest.tools.networkconnection.ConnectionState
import com.example.CoTest.tools.networkconnection.connectivityState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent

@Composable
fun HomeScreen(
    navController: NavController
) {
    // internet connection
    val viewModel: HomeViewModel = koinViewModel()
    val activity = (LocalContext.current as? Activity)
    val progressBar = remember { mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(false) }
    val pagedItems: LazyPagingItems<UserModel> =
        viewModel.loadUsesList().collectAsLazyPagingItems()

    BackHandler(enabled = (currentRoute(navController) == Screen.HomeNav.route)) {
        openDialog.value = true
    }
    Column(modifier = Modifier.background(DefaultBackgroundColor)) {
        //     CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("LazyColumn")
                .padding(start = 5.dp, end = 5.dp)
        ) {
            items(pagedItems) { user ->
                user?.let {
                    UserItem(user, navController)
                }
            }
        }
    }
    if (openDialog.value) {
        ExitAlertDialog(navController, {
            openDialog.value = it
        }, {
            activity?.finish()
        })

    }
    pagedItems.pagingLoadingState {
        progressBar.value = it
    }
}

@Composable
fun UserItem(user: UserModel, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navController.navigate(Screen.Detail.route.plus("/${user.id}"))
            },
        verticalAlignment = Alignment.CenterVertically,

        ) {
        CoilImage(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .padding(8.dp),
            imageModel = { user.picture },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "user item",
                colorFilter = null,
            ),
            component = rememberImageComponent {
                +CircularRevealPlugin(
                    duration = 800
                )
            },
        )
        Column {
            Text(
                text = "${user.firstName} ${user.lastName}",
                style = Typography().bodyMedium.copy(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = user.phone,
                style = Typography().bodyMedium.copy(fontSize = 14.sp)
            )
        }


    }
}