package com.example.mynewsapp.di

import android.app.Application
import androidx.room.Room
import com.example.mynewsapp.data.local.NewsDao
import com.example.mynewsapp.data.local.NewsDatabase
import com.example.mynewsapp.data.local.NewsTypeConvertor
import com.example.mynewsapp.data.manager.LocalUserManagerImpl
import com.example.mynewsapp.data.remote.dto.NewsApi
import com.example.mynewsapp.data.repository.NewsRepositoryImpl
import com.example.mynewsapp.domain.manager.LocalUserManager
import com.example.mynewsapp.domain.repository.NewsRepository
import com.example.mynewsapp.domain.usecases.app_entry.AppEntryUseCases
import com.example.mynewsapp.domain.usecases.app_entry.ReadAppEntry
import com.example.mynewsapp.domain.usecases.app_entry.SaveAppEntry
import com.example.mynewsapp.domain.usecases.news.DeleteArticle
import com.example.mynewsapp.domain.usecases.news.GetNews
import com.example.mynewsapp.domain.usecases.news.NewsUseCases
import com.example.mynewsapp.domain.usecases.news.SearchNews
import com.example.mynewsapp.domain.usecases.news.SelectArticle
import com.example.mynewsapp.domain.usecases.news.SelectArticles
import com.example.mynewsapp.domain.usecases.news.UpsertArticle
import com.example.mynewsapp.util.Constants.BASE_URL
import com.example.mynewsapp.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi,newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao


}