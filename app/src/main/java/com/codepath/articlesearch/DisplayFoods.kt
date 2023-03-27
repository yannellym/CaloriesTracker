package com.codepath.articlesearch

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// because Article and FoodEntity don't match up, we need to create this class to pass data around
@Parcelize
data class DisplayFoods(
    val name: String?,
    val calories: String?,
) : Parcelable

// we need to update DetailsActivity, MainActivity and ArticleAdapter to use
// our new data class for all UI related tasks.

