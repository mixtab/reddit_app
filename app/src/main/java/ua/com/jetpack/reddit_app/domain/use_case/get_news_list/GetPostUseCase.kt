package ua.com.jetpack.reddit_app.domain.use_case.get_news_list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ua.com.jetpack.reddit_app.common.Resource
import ua.com.jetpack.reddit_app.data.remote.dto.toPosts
import ua.com.jetpack.reddit_app.domain.model.Posts
import ua.com.jetpack.reddit_app.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    operator fun invoke(limit:Int, after: String): Flow<Resource<Posts>> = flow {
        try {
            emit(Resource.Loading<Posts>())
            val listNews = repository.getPosts(limit, after).toPosts()
            emit(Resource.Success<Posts>(listNews))
        } catch (e: HttpException) {
            emit(Resource.Error<Posts>(e.localizedMessage?:"ERROR"))
        } catch (e: HttpException) {
            emit(Resource.Error<Posts>("Please check your internet connection."))
        }
    }
}