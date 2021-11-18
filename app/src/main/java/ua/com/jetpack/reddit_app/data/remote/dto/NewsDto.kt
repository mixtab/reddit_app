package ua.com.jetpack.reddit_app.data.remote.dto

import ua.com.jetpack.reddit_app.domain.model.Data
import ua.com.jetpack.reddit_app.domain.model.News

data class NewsDto(
    val data: Data,
    val kind: String
)

fun NewsDto.toNews(): News {
    return News(
        data = data,
        kind = kind
    )

}
