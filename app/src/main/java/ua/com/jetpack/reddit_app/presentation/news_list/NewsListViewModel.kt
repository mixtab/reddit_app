package ua.com.jetpack.reddit_app.presentation.news_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ua.com.jetpack.reddit_app.common.Resource
import ua.com.jetpack.reddit_app.domain.use_case.get_news_list.GetNewsListUseCase
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase
) : ViewModel() {

    private val _state = MutableLiveData<NewsListState>()
    val state: LiveData<NewsListState> = _state

    init {
        getNews()
    }

    private fun getNews() {
        getNewsListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = NewsListState(news = result.data)
                }
                is Resource.Error -> {
                    _state.value = NewsListState(error = result.message ?: "Error")
                }
                is Resource.Loading -> {
                    _state.value = NewsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}