package com.loyds.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.loyds.model.data.Article
    
@Entity(
    tableName = "articles",
)
data class ArticleEntity(
    @PrimaryKey
    val id: String,
    val thumbnail: String,
    val sectionName: String,
    val title: String,
    val body: String,
    val published: Long,
    val favourite: Boolean,

)

fun ArticleEntity.asExternalModel() = Article(
    id = id,
    thumbnail = thumbnail,
    sectionName = sectionName,
    title = title,
    body = body,
    published = published,
    favourite = favourite,
)

fun Article.toEntity() = ArticleEntity(
    id = id,
    thumbnail = thumbnail,
    sectionName = sectionName,
    title = title,
    body = body,
    published = published,
    favourite = true,
)