package com.example.studyapproom.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Android_table")
data class AndroidLesson(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id: Int,
    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name = "Details")
    val details: String
)