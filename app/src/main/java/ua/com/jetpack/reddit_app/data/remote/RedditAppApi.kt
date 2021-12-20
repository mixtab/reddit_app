package ua.com.jetpack.reddit_app.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ua.com.jetpack.reddit_app.data.remote.dto.PostsDto

interface RedditAppApi {
    @GET("/top.json")
    suspend fun getPosts(
        @Query("limit") limit: Int,
        @Query("after") after: String,
    ): PostsDto
}