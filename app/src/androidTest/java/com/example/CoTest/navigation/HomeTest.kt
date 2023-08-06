package com.example.CoTest.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.action.ViewActions.swipeUp
import com.example.CoTest.presentation.home.HomeScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupRallyNavHost() {
        composeTestRule.setContent {
            navController =
                TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            HomeScreen(navController = navController)
        }
    }
    @Test
    fun testListScroll() {
        composeTestRule.onNodeWithTag("LazyColumn")
            .performTouchInput{ swipeUp() }

    }

}