package com.example.data.repository

import com.example.data.di.repositoryTestModule
import com.example.domain.loadUseCase.UserRepository
import com.example.domain.repo.model.RepoUserModel
import com.example.domain.repo.model.UserDetailRepoModel
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito

class UserRepositoryTest : KoinTest {
    private val userRepository: com.example.domain.loadUseCase.UserRepository by inject()

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