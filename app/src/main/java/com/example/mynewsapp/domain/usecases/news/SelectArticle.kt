package com.example.mynewsapp.domain.usecases.news

import com.example.mynewsapp.domain.model.Article
import com.example.mynewsapp.domain.repository.NewsRepository

data class SelectArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article?{
        return newsRepository.selectArticle(url)
    }

}