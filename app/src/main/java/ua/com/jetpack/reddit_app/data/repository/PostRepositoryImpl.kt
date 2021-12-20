package ua.com.jetpack.reddit_app.data.repository

import ua.com.jetpack.reddit_app.data.remote.RedditAppApi
import ua.com.jetpack.reddit_app.data.remote.dto.PostsDto
import ua.com.jetpack.reddit_app.domain.repository.PostsRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
        private val api: RedditAppApi
) : PostsRepository {
    override suspend fun getPosts(limit: Int, after: String): PostsDto {
        return api.getPosts(limit,after)
    }
}