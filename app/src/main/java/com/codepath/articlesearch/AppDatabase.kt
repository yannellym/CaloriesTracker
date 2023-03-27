package com.codepath.articlesearch

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
// BUILDS THE DATABASE
//AppDatabase defines the database configuration and serves as the app's main access point
//to the persisted data. The database class must satisfy the following conditions: The class must be
//annotated with a @Database annotation that includes an entities array that lists all of the data
//entities associated with the database. In our case, just the FoodEntity type.
//The class must be an abstract class that extends RoomDatabase. For each DAO class that is associated
//with the database, the database class must define an abstract method that has zero arguments
//and returns an instance of the DAO class.


// Annotates the class to specify the database entities and version
@Database(entities = [FoodEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao // Declares an abstract method that returns an instance of the FoodDao interface

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "Food-db"
            ).build()
        }
    }
}