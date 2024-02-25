package com.example.mynewsapp.domain.usecases.news

import com.example.mynewsapp.domain.model.Article
import com.example.mynewsapp.domain.repository.NewsRepository

data class DeleteArticle (
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }

}