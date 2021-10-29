package com.example.studyapproom.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LessonDao {

    //Kotlin
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addKotlinLesson(kotlinLesson: KotlinLesson)

    @Update
    suspend fun updateKotlinLesson(kotlinLesson: KotlinLesson)

    @Delete
    suspend fun deleteKotlinLesson(kotlinLesson: KotlinLesson)

    @Query("SELECT * FROM Kotlin_table ORDER BY ID ASC")
    fun getAllKotlinLessons(): LiveData<List<KotlinLesson>>

    //Android
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAndroidLesson(androidLesson: AndroidLesson)


    @Update
    suspend fun updateAndroidLesson(androidLesson: AndroidLesson)

    @Delete
    suspend fun deleteAndroidLesson(androidLesson: AndroidLesson)

    @Query("SELECT * FROM Android_table ORDER BY ID ASC")
    fun getAllAndroidLessons(): LiveData<List<AndroidLesson>>

}