package com.loyds.data.di

import com.loyds.network.service.ArticleService
import com.loyds.database.dao.ArticleDao
import com.loyds.data.repository.ArticleRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providesArticleRepository(articleService: ArticleService, articleDao: ArticleDao, ) = ArticleRepository(articleService, articleDao, )

}
