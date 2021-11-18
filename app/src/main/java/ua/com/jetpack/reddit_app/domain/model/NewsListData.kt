package ua.com.jetpack.reddit_app.domain.model

import ua.com.jetpack.reddit_app.data.remote.dto.NewsDto

class NewsListData(
    val children: List<NewsDto>
)