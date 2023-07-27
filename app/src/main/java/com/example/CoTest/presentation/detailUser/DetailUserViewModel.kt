package com.example.CoTest.presentation.detailUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.CoTest.presentation.detailUser.action.DetailViewAction
import com.example.CoTest.tools.AsyncState
import com.example.domain.userDetail.UserDetailUseCase
import com.example.domain.userDetail.model.UserDetailModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DetailUserUiModel(var userDetail: AsyncState<UserDetailModel>? = null)

class DetailUserViewModel constructor(private val userDetailUseCase: UserDetailUseCase) :
    ViewModel() {
    private val _dataState: MutableStateFlow<DetailUserUiModel> =
        MutableStateFlow(
            DetailUserUiModel()
        )

    val dataState: StateFlow<DetailUserUiModel> get() = _dataState

    fun handleAction(action: DetailViewAction) {
        viewModelScope.launch {
            when (action) {
                is DetailViewAction.LoadUserDetail -> {
                    loadUserDetail(action.id)
                }
            }
        }
    }

    private fun loadUserDetail(id: String) = viewModelScope.launch {
        _dataState.update { uiState -> uiState.copy(AsyncState.Success(userDetailUseCase(id))) }
    }

}