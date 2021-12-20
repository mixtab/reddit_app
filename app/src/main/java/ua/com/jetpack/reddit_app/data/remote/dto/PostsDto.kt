package ua.com.jetpack.reddit_app.data.remote.dto

import ua.com.jetpack.reddit_app.domain.model.Data
import ua.com.jetpack.reddit_app.domain.model.Posts

data class PostsDto(
        val data: Data
)

fun PostsDto.toPosts(): Posts {
    return Posts(
            data = data
    )
}