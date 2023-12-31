package  com.example.CoTest.presentation.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.CoTest.tools.pagination.PagerService


class HomeViewModel constructor(private val pagerService: PagerService) :
    ViewModel() {
    fun loadUsesList() = Pager(
        pagingSourceFactory = { pagerService },
        config = PagingConfig(pageSize = 20)
    ).flow.cachedIn(viewModelScope)

}