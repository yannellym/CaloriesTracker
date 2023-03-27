package com.codepath.articlesearch

import android.app.Application

// The Application class in Android is the base class within an Android app that
// contains all other components such as activities and services.
class FoodApplication : Application() {
    // A lazy initialization here just means we don't create this variable until it needs to be used.
    val db by lazy { AppDatabase.getInstance(this) }
}