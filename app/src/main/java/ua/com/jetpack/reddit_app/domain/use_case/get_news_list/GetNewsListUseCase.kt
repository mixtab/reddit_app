package ua.com.jetpack.reddit_app.domain.use_case.get_news_list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ua.com.jetpack.reddit_app.common.Resource
import ua.com.jetpack.reddit_app.data.remote.dto.toNewsList
import ua.com.jetpack.reddit_app.data.repository.NewsRepositoryImpl
import ua.com.jetpack.reddit_app.domain.model.NewsList
import ua.com.jetpack.reddit_app.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<NewsList>> = flow {
        try {
            emit(Resource.Loading<NewsList>())
            val listNews = repository.getListNews().toNewsList()
            emit(Resource.Success<NewsList>(listNews))
        } catch (e: HttpException) {
            emit(Resource.Error<NewsList>(e.localizedMessage?:"ERROR"))
        } catch (e: HttpException) {
            emit(Resource.Error<NewsList>("Please check your internet connection."))
        }
    }
}