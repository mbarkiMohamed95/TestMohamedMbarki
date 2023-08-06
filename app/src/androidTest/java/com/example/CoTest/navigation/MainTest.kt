package com.example.CoTest.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.CoTest.presentation.mainScreen.MainScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainTest {

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
            MainScreen()
        }
    }

    @Test
    fun testSearch() {
        composeTestRule
            .onNodeWithTag("searchAction")
            .performClick()
        composeTestRule
            .onNodeWithTag("searchtext")
            .assertIsDisplayed()
    }


}