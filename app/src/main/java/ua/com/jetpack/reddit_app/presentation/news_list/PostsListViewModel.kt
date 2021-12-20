package ua.com.jetpack.reddit_app.presentation.news_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ua.com.jetpack.reddit_app.common.Resource
import ua.com.jetpack.reddit_app.domain.use_case.get_news_list.GetPostUseCase
import javax.inject.Inject

@HiltViewModel
class PostsListViewModel @Inject constructor(
    private val getNewsListUseCase: GetPostUseCase
) : ViewModel() {

    private val _state = MutableLiveData<PostsListState>()
    val state: LiveData<PostsListState> = _state

    private var limit: Int = 10
    private var after: String = ""

    init {
        getNews()
    }

    fun getNews() {
        getNewsListUseCase(limit,after).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PostsListState(news = result.data ?: null)
                    result.data?.let { after = it.data.after }
                }
                is Resource.Error -> {
                    _state.value = PostsListState(error = result.message ?: "Error")
                }
                is Resource.Loading -> {
                    _state.value = PostsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}