package com.loyds.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.loyds.database.model.ArticleEntity
import kotlinx.coroutines.flow.Flow   

@Dao
interface ArticleDao {


    @Query(value = "SELECT * FROM articles")
    fun getArticleEntities(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE favourite = 1")
    fun getFavouriteArticles(): Flow<List<ArticleEntity>>

    @Query(value = """SELECT * FROM articles WHERE id = :id""")
    suspend fun getArticleEntity(id: String): ArticleEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreArticleEntities(entities: List<ArticleEntity>): List<Long>

    @Query(value = """DELETE FROM articles WHERE id in (:ids)""")
    suspend fun deleteArticleEntities(ids: List<String>)
}