package com.codepath.articlesearch

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class BaseResponse(
    @SerialName("docs")
    val docs: List<Food>
)

@Keep
@Serializable
data class Food(
    @SerialName("foods")
    val food: String?,
    @SerialName("calories")
    val calories: String?,
)

