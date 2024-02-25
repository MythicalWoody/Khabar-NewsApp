package com.example.mynewsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.mynewsapp.R

data class Page(val title: String,
                val description: String,
                @DrawableRes val image: Int)

val pages = listOf(
    Page(
        title = "Get latest news on Crypto",
        description = "The crypto market is a jungle full of opportunities. Be a wise explorer, use Khabar to navigate your path.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "See what's the Scuttlebutt",
        description = "From bustling bazaars to bustling boardrooms, unveil the whispers morphing into global headlines.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Get your daily dose of healthy news",
        description = "Fuel your mind and body: Daily healthy news delivered fresh, keeping you informed and inspired on your wellness journey.",
        image = R.drawable.onboarding3
    )
)

