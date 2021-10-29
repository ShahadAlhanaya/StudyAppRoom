package com.example.studyapproom.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LessonViewModel(application: Application): AndroidViewModel(application) {

    val getAllKotlinLessons : LiveData<List<KotlinLesson>>
    val getAllAndroidLessons : LiveData<List<AndroidLesson>>
    private val repository : LessonRepository

    init {
        val lessonDao = LessonDatabase.getDatabase(application).lessonDao()
        repository = LessonRepository(lessonDao)
        getAllKotlinLessons = repository.getAllKotlinLessons
        getAllAndroidLessons = repository.getAllAndroidLessons
    }

    //Kotlin

    fun addKotlinLesson(kotlinLesson: KotlinLesson){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addKotlinLesson(kotlinLesson)
        }
    }

    fun updateKotlinLesson(kotlinLesson: KotlinLesson){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateKotlinLesson(kotlinLesson)
        }
    }

    fun deleteKotlinLesson(kotlinLesson: KotlinLesson){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteKotlinLesson(kotlinLesson)
        }
    }

    //Android

    fun addAndroidLesson(androidLesson: AndroidLesson){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAndroidLesson(androidLesson)
        }
    }

    fun updateAndroidLesson(androidLesson: AndroidLesson){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAndroidLesson(androidLesson)
        }
    }

    fun deleteAndroidLesson(androidLesson: AndroidLesson){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAndroidLesson(androidLesson)
        }
    }
}