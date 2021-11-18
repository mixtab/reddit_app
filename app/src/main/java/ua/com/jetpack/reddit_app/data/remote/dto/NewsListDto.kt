package ua.com.jetpack.reddit_app.data.remote.dto

import ua.com.jetpack.reddit_app.domain.model.NewsList
import ua.com.jetpack.reddit_app.domain.model.NewsListData

data class NewsListDto(
    val data: NewsListData
)
fun NewsListDto.toNewsList(): NewsList {
    return NewsList(
        data = data
    )
}