package ua.com.jetpack.reddit_app.domain.model

data class PostData(
    val author: String,
    val created: Double,
    val created_utc: Long,
    val num_comments: Int,
    val thumbnail: String,
    val title: String,
    val url: String,
)