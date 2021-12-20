package ua.com.jetpack.reddit_app.data.remote.dto

import ua.com.jetpack.reddit_app.domain.model.Data
import ua.com.jetpack.reddit_app.domain.model.Posts

data class PostDto(
    val data: Data,
    val kind: String
)

fun PostDto.toNews(): Posts {
    return Posts(
        data = data
    )

}
