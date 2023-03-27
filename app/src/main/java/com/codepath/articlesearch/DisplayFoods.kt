package com.codepath.articlesearch

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

// because Article and FoodEntity don't match up, we need to create this class to pass data around
@Parcelize
data class DisplayFoods(
    val name: String,
    val calories: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    companion object : Parceler<DisplayFoods> {

        override fun DisplayFoods.write(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(calories)
        }

        override fun create(parcel: Parcel): DisplayFoods {
            return DisplayFoods(parcel)
        }
    }
}
// we need to update DetailsActivity, MainActivity and ArticleAdapter to use
// our new data class for all UI related tasks.

