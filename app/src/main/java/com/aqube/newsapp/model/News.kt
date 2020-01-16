package com.aqube.newsapp.model

import com.aqube.newsapp.base.network.Model
import com.google.gson.annotations.SerializedName

/**
 * News data class
 */
data class News(
    @SerializedName("source")
    var source: Source? = null,
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("urlToImage")
    var urlToImage: String? = null,
    @SerializedName("publishedAt")
    var publishedAt: String? = null,
    @SerializedName("content")
    var content: String? = null
): Model