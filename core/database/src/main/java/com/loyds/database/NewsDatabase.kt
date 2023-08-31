package com.loyds.database

import androidx.room.Database
import androidx.room.RoomDatabase

import com.loyds.database.dao.ArticleDao
import com.loyds.database.model.ArticleEntity

@Database(
    entities = [
        ArticleEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}