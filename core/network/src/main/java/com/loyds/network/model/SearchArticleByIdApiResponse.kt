package com.loyds.network.model

import com.loyds.model.data.Article
import kotlinx.serialization.Serializable

@Serializable
data class SearchArticleByIdApiResponse(
    val response: SearchArticleByIdApiResponseResponse,

)

@Serializable
data class SearchArticleByIdApiResponseResponse(
    val status: String,
    val userTier: String,
    val total: Double,
    val content: SearchArticleByIdApiResponseResponseContent,

)

@Serializable
data class SearchArticleByIdApiResponseResponseContent(
    val id: String,
    val type: String,
    val sectionId: String,
    val sectionName: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String,
    val apiUrl: String,
    val fields: SearchArticleByIdApiResponseResponseContentFields,
    val isHosted: Boolean,

)

@Serializable
data class SearchArticleByIdApiResponseResponseContentFields(
    val headline: String,
    val body: String,
    val thumbnail: String,
)


fun SearchArticleByIdApiResponse.map(): Article {
    this.response.content.let {
        return Article(
            it.id,
            it.fields.thumbnail,
            it.sectionName,
            it.fields.headline,
            it.fields.body,
            0L,
            false
        )
    }
}
