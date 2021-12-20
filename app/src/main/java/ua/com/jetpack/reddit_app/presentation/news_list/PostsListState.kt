package ua.com.jetpack.reddit_app.presentation.news_list

import ua.com.jetpack.reddit_app.domain.model.Posts

class PostsListState(
    val isLoading: Boolean = false,
    val news: Posts? = null,
    val error: String = ""
) {
}