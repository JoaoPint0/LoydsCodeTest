package com.loyds.database.di

import com.loyds.database.dao.ArticleDao
import com.loyds.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesArticleDao(
        database: NewsDatabase,
    ): ArticleDao = database.articleDao()


}