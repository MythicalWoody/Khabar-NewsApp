package com.example.mynewsapp.presentation.details.components

import com.example.mynewsapp.domain.model.Article

sealed class DetailsEvent  {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}