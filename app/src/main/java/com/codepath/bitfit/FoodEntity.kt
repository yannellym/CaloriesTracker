package com.codepath.bitfit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Each instance of an Entity data class represents a row in a table for foods in the app's database.

// Essentially, we're creating a "template" for each row in our future database table, by specifying the columns.
// (This is somewhat similar to the models we used when serializing JSON!)



// all users need a unique ID to be identified within the table.
// for this, we'll use a long to generate a unique iD
@Entity(tableName = "foods_table")
data class FoodEntity(
    @ColumnInfo(name = "food") val name: String?,
    @ColumnInfo(name = "calories") val calories: String?,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)
