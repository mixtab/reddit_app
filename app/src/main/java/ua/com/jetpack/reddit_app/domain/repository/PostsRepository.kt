package ua.com.jetpack.reddit_app.domain.repository

import ua.com.jetpack.reddit_app.data.remote.dto.PostsDto

interface PostsRepository {
   suspend fun getPosts(limit:Int, after: String):PostsDto

}