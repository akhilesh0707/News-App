package com.aqube.newsapp.model

import com.aqube.newsapp.base.network.Model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    var id: Any? = null,
    @SerializedName("name")
    var name: String? = null
): Model