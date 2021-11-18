package ua.com.jetpack.reddit_app.data.repository

import ua.com.jetpack.reddit_app.data.remote.RedditAppApi
import ua.com.jetpack.reddit_app.data.remote.dto.NewsDto
import ua.com.jetpack.reddit_app.data.remote.dto.NewsListDto
import ua.com.jetpack.reddit_app.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: RedditAppApi
) : NewsRepository {
    override suspend fun getListNews(): NewsListDto {
        return api.getNewsList()
    }
}