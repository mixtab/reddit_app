package ua.com.jetpack.reddit_app.data.remote

import retrofit2.http.GET
import ua.com.jetpack.reddit_app.data.remote.dto.NewsDto
import ua.com.jetpack.reddit_app.data.remote.dto.NewsListDto

interface RedditAppApi {
    @GET("/top.json")
    suspend fun getNewsList():NewsListDto
}