package com.example.data.network


import com.example.data.di.NetworkingTestModule
import com.example.data.networking.services.ApiServices
import com.example.data.networking.userInfo.model.Info
import com.example.data.networking.userInfo.model.Results
import com.example.data.networking.userInfo.model.UserResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Response
import com.google.common.truth.Truth.assertThat
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource
import org.junit.After
import org.koin.core.context.stopKoin
import org.mockito.Mockito

class UserNetworkManagerTest : KoinTest {
    private val apiServices: ApiServices by inject()
    private val successResponse =
        Response.success(UserResponse(listOf<Results>(), Info("", 1, 2, 2.0)))
    private val errorResponse = Response.error<UserResponse>(400,ErrorResponseBody())

    @Before
    fun before() {
        startKoin {
            modules(NetworkingTestModule)
        }

    }

    @Test
    fun testApiSuccessResponse() = runBlocking {

        Mockito.`when`(apiServices.loadUserInfo(1, 20)).thenReturn( successResponse)

        assertThat(apiServices.loadUserInfo(1, 20)).isEqualTo(successResponse)
    }
    @Test
    fun testApiFailedResponse() = runBlocking {

        Mockito.`when`(apiServices.loadUserInfo(1, 20)).thenReturn( errorResponse)

        assertThat(apiServices.loadUserInfo(1, 20)).isEqualTo(errorResponse)
    }
    @After
    fun afterTest(){
        stopKoin()
    }

}
class ErrorResponseBody():ResponseBody(){
    override fun contentLength(): Long =1

    override fun contentType(): MediaType? =null

    override fun source(): BufferedSource {
        TODO("Not yet implemented")
    }

}