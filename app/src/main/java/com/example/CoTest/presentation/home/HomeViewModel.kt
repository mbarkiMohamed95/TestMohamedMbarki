package  com.example.CoTest.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotMutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.loadUseCase.LoadUsersListUseCase
import com.example.CoTest.presentation.home.action.HomeAction
import com.example.domain.loadUseCase.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch


class HomeViewModel constructor(private val loadUsersListUseCase: LoadUsersListUseCase) :
    ViewModel() {
    fun loadUsesList(isConnected:Boolean) = loadUsersListUseCase(isConnected).cachedIn(viewModelScope)

}