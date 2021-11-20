package ua.com.jetpack.reddit_app.presentation.news_list

import ua.com.jetpack.reddit_app.domain.model.NewsList

class NewsListState(
    val isLoading: Boolean = false,
    val news: NewsList? = null,
    val error: String = ""
) {
}