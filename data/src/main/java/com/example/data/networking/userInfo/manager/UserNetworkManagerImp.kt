package com.example.data.networking.userInfo.manager

import androidx.paging.PagingState
import com.example.data.networking.services.ApiServices
import com.example.data.networking.userInfo.model.Results
import retrofit2.HttpException
import java.io.IOException


class UserNetworkManagerImp constructor(private val apiService: ApiServices) :
    UserNetworkManager() {
    override fun getRefreshKey(state: PagingState<Int, Results>): Int? =
         state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> =
        try {
            val nextPage = params.key ?: 0
            val users = apiService.loadUserInfo(nextPage)
            users.body()?.let {
                LoadResult.Page(
                    data = it.results,
                    prevKey = null,
                    nextKey = if (it.results.isNotEmpty()) it.info.page + 1 else null
                )
            } ?: LoadResult.Error(Exception("Failed"))

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (httpException: HttpException) {
            LoadResult.Error(httpException)
        }


}