package com.codepath.articlesearch

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Next we will represent "docs" with another data class.
// This will map to the base response, which will include the array of documents (articles)
// Each array element will be an individual article.
@Keep
@Serializable
data class BaseResponse(
    @SerialName("docs")
    val docs: List<Food>
)

//We've made it down to the Article layer! Now, we will need to identify
// which pieces of information we need from the JSON response for each of the pieces
// that will be displayed on screen.
@Keep
@Serializable
data class Food(
    @SerialName("foods")
    val food: String?,
    @SerialName("calories")
    val calories: String?,
)

