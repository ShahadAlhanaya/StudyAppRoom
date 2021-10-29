package com.example.studyapproom.data

import androidx.lifecycle.LiveData

class LessonRepository( private val lessonDao: LessonDao) {

    //Kotlin

    val getAllKotlinLessons : LiveData<List<KotlinLesson>> = lessonDao.getAllKotlinLessons()

    suspend fun addKotlinLesson(kotlinLesson: KotlinLesson){
        lessonDao.addKotlinLesson(kotlinLesson)
    }

    suspend fun updateKotlinLesson(kotlinLesson: KotlinLesson){
        lessonDao.updateKotlinLesson(kotlinLesson)
    }

    suspend fun deleteKotlinLesson(kotlinLesson: KotlinLesson){
        lessonDao.deleteKotlinLesson(kotlinLesson)
    }



    //Android

    val getAllAndroidLessons : LiveData<List<AndroidLesson>> = lessonDao.getAllAndroidLessons()

    suspend fun addAndroidLesson(androidLesson: AndroidLesson){
        lessonDao.addAndroidLesson(androidLesson)
    }

    suspend fun updateAndroidLesson(androidLesson: AndroidLesson){
        lessonDao.updateAndroidLesson(androidLesson)
    }

    suspend fun deleteAndroidLesson(androidLesson: AndroidLesson){
        lessonDao.deleteAndroidLesson(androidLesson)
    }

}