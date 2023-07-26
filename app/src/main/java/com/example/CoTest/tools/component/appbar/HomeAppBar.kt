package com.example.CoTest.tools.component.appbar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import com.example.CoTest.res.theme.Purple40

@Composable
fun HomeAppBar(title: String, openFilters: () -> Unit) {
    TopAppBar(
        backgroundColor = Purple40,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
            )
        }
        /*actions = {
            IconButton(onClick = openFilters) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        }*/
    )
}
