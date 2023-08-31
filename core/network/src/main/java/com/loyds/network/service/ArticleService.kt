package com.loyds.network.service


import com.loyds.network.model.SearchArticlesApiResponse
import retrofit2.http.GET
import com.loyds.network.model.SearchArticleByIdApiResponse
import retrofit2.http.Query
import retrofit2.http.Path

interface ArticleService {

    @GET("search?page-size=50")
    suspend fun searchArticles(@Query("q") q: String = "fintech,brexit", @Query("show-fields") showFields: String = "body,headline,thumbnail", @Query("page-size") pageSize: String = "50", ): SearchArticlesApiResponse

    @GET("/{id}")
    suspend fun searchArticleById(@Path("id", encoded=true) id: String, @Query("show-fields") showFields: String = "body,headline,thumbnail", ): SearchArticleByIdApiResponse

}