package ua.com.jetpack.reddit_app.domain.model

data class Data(
    val after: String = "",
    val children: List<Children>,
)