package com.loyds.model.data
    
data class Article(
    val id: String,
    val thumbnail: String,
    val sectionName: String,
    val title: String,
    val body: String,
    val published: Long,
    val favourite: Boolean,
)