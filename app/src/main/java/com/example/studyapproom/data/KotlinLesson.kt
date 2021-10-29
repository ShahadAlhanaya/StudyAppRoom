package com.example.studyapproom.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kotlin_table")
data class KotlinLesson(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id: Int,
    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name = "Details")
    val details: String
)