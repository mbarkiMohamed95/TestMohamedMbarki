package com.example.CoTest.tools.component

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.CoTest.tools.AsyncState


fun <T:Any> LazyListScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyItemScope.(value: T?) -> Unit
){
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems[index])
    }
}

@Composable
fun <T : Any> LazyPagingItems<T>.pagingLoadingState(
    isLoaded: (pagingState: Boolean) -> Unit,
) {
    this.apply {
        when {
            // data is loading for first time
            loadState.refresh is LoadState.Loading -> {
                isLoaded(true)
            }
            // data is loading for second time or pagination
            loadState.append is LoadState.Loading -> {
                isLoaded(false)
            }
            loadState.refresh is LoadState.NotLoading -> {
                isLoaded(false)
            }
            loadState.append is LoadState.NotLoading -> {
                isLoaded(false)
            }
        }
    }
}

fun <T : Any> MutableState<AsyncState<T>?>.pagingLoadingState(isLoaded: (pagingState: Boolean) -> Unit) {
    when (this.value) {
        is AsyncState.Success<T> -> {
            isLoaded(false)
        }
        is AsyncState.Loading -> {
            isLoaded(true)
        }
        is AsyncState.Failure -> {
            isLoaded(false)
        }
        else -> {

        }
    }
}

fun Modifier.conditional(condition : Boolean, modifier : Modifier.() -> Modifier) : Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}