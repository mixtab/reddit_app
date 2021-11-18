package ua.com.jetpack.reddit_app.domain.model

data class Data(
    val author: String,
    val num_comments: Int,
    val preview: Preview,
    val created: Double,
    val created_utc: Double,
)