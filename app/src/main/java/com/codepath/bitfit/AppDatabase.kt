package com.codepath.bitfit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

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
        // Marks the JVM backing field of the annotated property as volatile, meaning that writes to this field are immediately made visible to other threads.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        // builds our database
        private fun buildDatabase(context: Context): AppDatabase {
            val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "Food-db"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        // define example foods
                        val foods = listOf(
                            FoodEntity(name = "Banana", calories = "105"),
                            FoodEntity(name = "Apple", calories = "95"),
                            FoodEntity(name = "Chicken breast", calories = "165"),
                            FoodEntity(name = "Salmon", calories = "206")
                        )

                        // insert the example foods into the database
                        val dao = AppDatabase.getInstance(context).foodDao()
                        dao.insertAll(foods)
                    }
                })
                .build()

            return db
        }
    }
}
