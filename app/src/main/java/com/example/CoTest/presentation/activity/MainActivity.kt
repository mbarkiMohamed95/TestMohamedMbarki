package com.example.CoTest.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.CoTest.presentation.mainScreen.MainScreen
import com.example.CoTest.res.theme.ExempleKoinTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val splashViewModel: MainActivityViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition { splashViewModel.isLoading.value }
        }
        setContent {
            ExempleKoinTheme {
                MainScreen()
            }
        }
    }
}