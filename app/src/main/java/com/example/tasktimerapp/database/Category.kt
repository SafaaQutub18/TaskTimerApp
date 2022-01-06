package com.example.tasktimerapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Categories")
data class Category(
    @PrimaryKey(autoGenerate = false)
    val categoryName: String,
    //val taskPK: Int,
    val categoryImage: Int
)
