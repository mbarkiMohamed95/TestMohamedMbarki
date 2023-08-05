package com.example.CoTest.tools.pagination

import androidx.paging.PagingState
import coil.network.HttpException
import com.example.domain.loadUseCase.model.UserModel
import com.example.domain.tools.paging.PagerService
import java.io.IOException

class PagerServicesImp(private val pagedList: List<UserModel>, private val loadedPage: Int) :
    PagerService() {
    var nextPage = 0

    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> =
        try {
            nextPage = params.key ?: 0
            val users = pagedList
            pagedList.let {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = loadedPage
                )
            } ?: LoadResult.Error(Exception("Failed"))

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (httpException: HttpException) {
            LoadResult.Error(httpException)
        }
}