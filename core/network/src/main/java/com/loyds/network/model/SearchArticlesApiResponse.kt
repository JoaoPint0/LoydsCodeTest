package com.loyds.network.model

import java.util.Date
import kotlinx.serialization.Serializable
import com.loyds.model.data.Article
    

@Serializable
data class SearchArticlesApiResponse(
    val response: SearchArticlesApiResponseResponse,

)

@Serializable
data class SearchArticlesApiResponseResponse(
    val status: String,
    val userTier: String,
    val total: Double,
    val startIndex: Double,
    val pageSize: Double,
    val currentPage: Double,
    val pages: Double,
    val orderBy: String,
    val results: List<SearchArticlesApiResponseResponseResults>,

)

@Serializable
data class SearchArticlesApiResponseResponseResults(
    val id: String,
    val type: String,
    val sectionId: String,
    val sectionName: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String,
    val apiUrl: String,
    val fields: SearchArticlesApiResponseResponseResultsFields,
    val isHosted: Boolean,
)

@Serializable
data class SearchArticlesApiResponseResponseResultsFields(
    val headline: String,
    val body: String,
    val thumbnail: String,

)


fun SearchArticlesApiResponse.map(): List<Article> {
    return this.response.results.map {
        Article(
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
