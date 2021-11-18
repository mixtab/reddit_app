package ua.com.jetpack.reddit_app.domain.repository

import ua.com.jetpack.reddit_app.data.remote.dto.NewsDto
import ua.com.jetpack.reddit_app.data.remote.dto.NewsListDto

interface NewsRepository {
   suspend fun getListNews():NewsListDto

}