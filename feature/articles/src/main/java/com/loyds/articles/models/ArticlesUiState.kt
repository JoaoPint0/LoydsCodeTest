package com.loyds.articles.models

import com.loyds.model.data.Article

data class ArticlesUiState(

    val content: List<Article> = emptyList(),
)