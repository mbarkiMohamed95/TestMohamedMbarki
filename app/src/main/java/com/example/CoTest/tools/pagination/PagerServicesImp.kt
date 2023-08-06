package com.example.CoTest.tools.pagination

import androidx.paging.PagingState
import coil.network.HttpException
import com.example.domain.loadUseCase.LoadUsersListUseCase
import com.example.domain.loadUseCase.model.UserModel
import com.example.domain.tools.paging.PagerService
import java.io.IOException

class PagerServicesImp(private val loadUsersListUseCase: LoadUsersListUseCase) :
    PagerService() {
    var nextPage = 0

    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        try {
            nextPage = params.key ?: 0
            loadUsersListUseCase(nextPage).onSuccess {
                return LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isNotEmpty()) it[0].loadedPageNumber + 1 else null
                )
            }.onFailure {
                return LoadResult.Error(Exception("Failed"))
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (httpException: HttpException) {
            return LoadResult.Error(httpException)
        }
        return LoadResult.Error(Exception("Failed"))
    }

}