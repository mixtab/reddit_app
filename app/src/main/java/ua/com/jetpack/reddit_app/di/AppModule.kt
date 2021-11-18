package ua.com.jetpack.reddit_app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.com.jetpack.reddit_app.common.Constants
import ua.com.jetpack.reddit_app.data.remote.RedditAppApi
import ua.com.jetpack.reddit_app.data.repository.NewsRepositoryImpl
import ua.com.jetpack.reddit_app.domain.repository.NewsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): RedditAppApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RedditAppApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api: RedditAppApi):NewsRepository{
        return NewsRepositoryImpl(api)
    }

}