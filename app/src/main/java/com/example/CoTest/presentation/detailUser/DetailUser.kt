package com.example.CoTest.presentation.detailUser

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.CoTest.R
import com.example.CoTest.presentation.detailUser.action.DetailViewAction
import com.example.CoTest.res.progressBar.CircularIndeterminateProgressBar
import com.example.CoTest.res.theme.DefaultBackgroundColor
import com.example.CoTest.res.theme.FontColor
import com.example.CoTest.tools.AsyncState
import com.example.CoTest.tools.component.text.SubtitlePrimary
import com.example.CoTest.tools.component.text.SubtitleSecondary
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailUser(navController: NavController, userId: String) {
    val viewModel: DetailUserViewModel = koinViewModel()
    val progressBar = remember { mutableStateOf(false) }
    val userDetail = viewModel.dataState.collectAsState().value.userDetail

    LaunchedEffect(true) {
        viewModel.handleAction(DetailViewAction.LoadUserDetail(userId))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                DefaultBackgroundColor
            )
    ) {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)

        when (userDetail) {
            is AsyncState.Success -> {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    CoilImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        imageModel = { userDetail.data?.picture },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                            contentDescription = "User detail",
                            colorFilter = null,
                        ),
                        component = rememberImageComponent {
                            +CircularRevealPlugin(
                                duration = 800
                            )
                        },
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp)
                    ) {
                        Text(
                            text = userDetail.data?.fullName ?: "",
                            modifier = Modifier.padding(top = 10.dp),
                            color = FontColor,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.W700,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp, top = 10.dp)
                        ) {

                            Column(Modifier.fillMaxWidth()) {
                                SubtitleSecondary(
                                    text = stringResource(R.string.email)
                                )
                                SubtitlePrimary(
                                    text = userDetail.data?.email ?: "",
                                )
                            }
                            Column(Modifier.fillMaxWidth()) {
                                SubtitleSecondary(
                                    text = stringResource(R.string.phone_number)
                                )
                                SubtitlePrimary(
                                    text = userDetail.data?.phone ?: "",
                                )
                            }
                            Column(Modifier.fillMaxWidth()) {
                                SubtitleSecondary(
                                    text = stringResource(R.string.adress)
                                )
                                SubtitlePrimary(
                                    text = userDetail.data?.location ?: ""
                                )
                            }
                            Column(Modifier.fillMaxWidth()) {
                                SubtitleSecondary(
                                    text = stringResource(R.string.gender)
                                )
                                SubtitlePrimary(
                                    text = userDetail.data?.gender ?: ""
                                )
                            }
                        }
                    }
                }
            }

            else -> {

            }


        }
    }
}


