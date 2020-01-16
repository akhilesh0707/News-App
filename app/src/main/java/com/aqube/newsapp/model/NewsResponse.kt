package com.aqube.newsapp.model

import com.aqube.newsapp.base.network.Model
import com.google.gson.annotations.SerializedName

/**
 * NewsResponse data class
 */
data class NewsResponse(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("totalResults")
    var totalResults: Int,
    @SerializedName("articles")
    var news: List<News>
) : Model