package  com.example.CoTest.presentation.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.loadUseCase.LoadUsersListUseCase



class HomeViewModel constructor(private val loadUsersListUseCase: LoadUsersListUseCase) :
    ViewModel() {
    fun loadUsesList(isConnected:Boolean) = loadUsersListUseCase(isConnected).cachedIn(viewModelScope)

}