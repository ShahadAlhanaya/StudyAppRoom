package com.example.studyapproom.data

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@Database(entities = [KotlinLesson::class, AndroidLesson::class], version = 1, exportSchema = false)
abstract class LessonDatabase : RoomDatabase() {

    abstract fun lessonDao(): LessonDao

    companion object {
        @Volatile
        private var INSTANCE: LessonDatabase? = null

        fun getDatabase(context: Context): LessonDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                LessonDatabase::class.java,
                "Lessons_database"
            )
                // prepopulate the database
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
                        CoroutineScope(IO).launch {
                            for(i in 0..2){
                                getDatabase(context).lessonDao().addKotlinLesson(KOTLIN_LESSONS[i])
                                getDatabase(context).lessonDao().addAndroidLesson(ANDROID_LESSONS[i])
                            }

                        }
                    }
                })
                .build()

        val KOTLIN_LESSONS = listOf(
            KotlinLesson(0, "var and val", "Declaring variables."),
            KotlinLesson(0, "User Input", "Getting user input."),
            KotlinLesson(0, "Strings", "String concatenations, interpolation, and methods.")
        )

        val ANDROID_LESSONS = listOf(
            AndroidLesson(0, "var and val", "Declaring variables."),
            AndroidLesson(0, "User Input", "Getting user input."),
            AndroidLesson(0, "Strings", "String concatenations, interpolation, and methods.")
        )
//        fun getDatabase(context: Context): LessonDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    LessonDatabase::class.java,
//                    "Lessons_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
    }
}