package com.example.CoTest.di

import com.example.CoTest.presentation.activity.MainActivityViewModel
import com.example.CoTest.presentation.detailUser.DetailUserViewModel
import com.example.CoTest.presentation.home.HomeViewModel
import com.example.CoTest.presentation.mainScreen.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsProvider = module {
    viewModel() { HomeViewModel(get()) }
    viewModel() { MainViewModel(get()) }
    viewModel() { MainActivityViewModel() }
    viewModel() { DetailUserViewModel(get()) }
}