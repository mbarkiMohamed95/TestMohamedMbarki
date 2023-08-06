package com.example.CoTest.presentation.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.CoTest.res.theme.DefaultBackgroundColor
import com.example.CoTest.routing.Screen
import com.example.CoTest.tools.component.ExitAlertDialog
import com.example.CoTest.tools.component.items
import com.example.CoTest.tools.component.pagingLoadingState
import com.example.CoTest.tools.navigation.currentRoute
import com.example.domain.repo.user.model.UserModelDto
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    navController: NavController
) {
    // internet connection
    val viewModel: HomeViewModel = koinViewModel()
    val activity = (LocalContext.current as? Activity)
    val progressBar = remember { mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(false) }

    val pagedItems: LazyPagingItems<UserModelDto> =
        viewModel.loadUsesList().collectAsLazyPagingItems()
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

 /*   var lifecycleEvent by remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            lifecycleEvent = event
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }

    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            viewModel.fetchNews()
        }
    }*/
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
fun UserItem(user: UserModelDto, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navController.navigate(Screen.Detail.route.plus("/${user.uuid}"))
            },
        verticalAlignment = Alignment.CenterVertically,

        ) {
        CoilImage(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .padding(8.dp),
            imageModel = { user.picture?.thumbnail },
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
                text = "${user.name?.first} ${user.name?.last}",
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