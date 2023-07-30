package com.example.data.repository

import com.example.data.di.repositoryTestModule
import com.example.data.network.ErrorResponseBody
import com.example.data.networking.userInfo.model.UserResponse
import com.example.data.repository.user.manager.UserRepository
import com.example.data.repository.user.model.RepoUserModel
import com.example.data.repository.user.model.RepoUserNameModel
import com.example.data.repository.user.model.UserDetailRepoModel
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.Result
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito
import retrofit2.Response

class UserRepositoryTest : KoinTest {
    private val userRepository: UserRepository by inject()

    private val successResponse =
        UserDetailRepoModel(
            "Mohamed Mbarki",
            "mbarki.mouhammed@gmail.com",
            "paris",
            "0648626802",
            "https://image.fr",
            "Homme"
        )

    private val successSearchResponse =
        listOf(RepoUserModel())

    @Before
    fun before() {
        startKoin {
            modules(repositoryTestModule)
        }
    }


    @Test
    fun testLoadUserByIdSuccessResponse() = runBlocking {
        Mockito.`when`(userRepository.loadUserById("1")).thenReturn(successResponse)
        Truth.assertThat(userRepository.loadUserById("1"))
            .isEqualTo(successResponse)
    }

    @Test
    fun testSearchUserSuccessResponse() = runBlocking {
        Mockito.`when`(userRepository.searchUser("1")).thenReturn(successSearchResponse)
        Truth.assertThat(userRepository.searchUser("1"))
            .isEqualTo(successSearchResponse)
    }

    @After
    fun afterTest() {
        stopKoin()
    }
}