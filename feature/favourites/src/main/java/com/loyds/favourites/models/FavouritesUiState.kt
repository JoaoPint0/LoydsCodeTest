package com.loyds.favourites.models

import com.loyds.model.data.Article

data class FavouritesUiState(

    val content: List<Article> = emptyList(),
)