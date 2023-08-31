package com.loyds.data.repository

import com.loyds.network.model.map
import com.loyds.network.service.ArticleService
import com.loyds.database.model.asExternalModel
import com.loyds.model.data.Article
import com.loyds.database.dao.ArticleDao
import com.loyds.database.model.ArticleEntity
import com.loyds.database.model.toEntity

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleRepository @Inject constructor(

    private val articleService: ArticleService,
    private val articleDao: ArticleDao,
) {
    suspend fun getArticleList(): Result<List<Article>> = runCatching {
        val result = articleService.searchArticles().map()

        result
    }

    fun getFavouriteArticles(): Flow<List<Article>> =
        articleDao.getFavouriteArticles().map { it.map(ArticleEntity::asExternalModel) }

    suspend fun addFavourite(article: Article) = runCatching {
        articleDao.insertOrIgnoreArticleEntities(listOf(article.toEntity()))
    }

    suspend fun removeFavourite(id: String) = runCatching {
        articleDao.deleteArticleEntities(listOf(id))
    }

    suspend fun getArticleById(id: String): Result<Article> = runCatching {
        val result = articleService.searchArticleById(id).map()
        val favourite = articleDao.getArticleEntity(id)
        result.copy(favourite = favourite != null)
    }

}