package ua.com.jetpack.reddit_app.domain.model

import ua.com.jetpack.reddit_app.data.remote.dto.NewsDto

data class Image(
    val resolutions: List<NewsDto>,
)