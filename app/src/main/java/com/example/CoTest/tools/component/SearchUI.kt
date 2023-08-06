package com.example.CoTest.tools.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.CoTest.res.theme.DefaultBackgroundColor
import com.example.CoTest.routing.Screen
import com.example.CoTest.tools.AsyncState
import com.example.domain.repo.model.UserModelDto
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent

@Composable
fun SearchUI(
    navController: NavController,
    searchData: AsyncState<List<UserModelDto>>?,
    itemClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize() // define max height
            .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
            .background(color = DefaultBackgroundColor)
            .padding(top = 8.dp)

    ) {
        when (searchData) {
            is AsyncState.Success -> {
                searchData.data?.let { data ->
                    items(items = data, itemContent = { item ->
                        Row(modifier = Modifier
                            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                            .clickable {
                                itemClick.invoke()
                                navController.navigate(
                                    Screen.Detail.route.plus(
                                        "/${item.uuid}"
                                    )
                                )
                            }) {
                            CoilImage(
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(80.dp),
                                imageModel = { item.picture },
                                imageOptions = ImageOptions(
                                    contentScale = ContentScale.Crop,
                                    alignment = Alignment.Center,
                                    contentDescription = "search item",
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
                                    text = item.name?.first + " " + item.name?.last,
                                    modifier = Modifier.padding(
                                        start = 8.dp,
                                        top = 4.dp
                                    ),
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    })

                }

            }

            else -> {

            }
        }
    }
}