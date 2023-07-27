package com.example.CoTest.presentation.mainScreen


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.CoTest.tools.AsyncState
import com.example.domain.loadUseCase.model.UserModel
import com.example.domain.searchUser.SearchUserUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch


class MainViewModel constructor(private val searchUserUseCase: SearchUserUseCase) : ViewModel() {
    private val _searchData: MutableState<AsyncState<List<UserModel>>?> = mutableStateOf(null)
    val searchData: State<AsyncState<List<UserModel>>?> get() = _searchData

    @FlowPreview
    fun searchApi(searchKey: String) = viewModelScope.launch {
        _searchData.value = AsyncState.Success(searchUserUseCase(searchKey))
    }

}