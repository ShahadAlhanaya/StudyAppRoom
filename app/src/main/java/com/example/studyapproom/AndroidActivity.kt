package com.example.studyapproom

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapproom.data.AndroidLesson
import com.example.studyapproom.data.KotlinLesson
import com.example.studyapproom.data.LessonViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AndroidActivity : AppCompatActivity() {

    private lateinit var lessonViewModel: LessonViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var androidAdapter: AndroidAdapter
    private lateinit var floatingAddButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)

        //actionbar
        val actionbar = supportActionBar!!
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        //set title
        title = "Android"


        //initialize recyclerView
        recyclerView = findViewById(R.id.android_recycler_view)
        androidAdapter = AndroidAdapter(this)
        recyclerView.adapter = androidAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //initialize view model
        lessonViewModel = ViewModelProvider(this).get(LessonViewModel::class.java)
        getAllAndroidLessons()


        floatingAddButton = findViewById(R.id.floatingActionButton_android)
        floatingAddButton.setOnClickListener {
            addLessonDialog()
        }
    }

    private fun getAllAndroidLessons() {
        lessonViewModel.getAllAndroidLessons.observe(this, { lessons ->
            androidAdapter.setData(lessons)
        })
    }

    private fun addAndroidLesson(title: String, details: String) {
        lessonViewModel.addAndroidLesson(AndroidLesson(0, title, details))
        Toast.makeText(applicationContext, "Added Successfully!", Toast.LENGTH_SHORT).show()
    }

    private fun updateAndroidLesson(id: Int,title: String, details: String) {
        lessonViewModel.updateAndroidLesson(AndroidLesson(id, title, details))
        Toast.makeText(applicationContext, "Updated Successfully!", Toast.LENGTH_SHORT).show()
    }

    private fun deleteAndroidLesson(id: Int,title: String, details: String) {
        lessonViewModel.deleteAndroidLesson(AndroidLesson(id, title, details))
        Toast.makeText(applicationContext, "Deleted Successfully!", Toast.LENGTH_SHORT).show()
    }

    private fun addLessonDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.add_dialog)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        //views
        val titleTextView = dialog.findViewById<TextView>(R.id.title_add_dialog)
        val detailsTextView = dialog.findViewById<TextView>(R.id.details_add_dialog)
        val addButton = dialog.findViewById<Button>(R.id.add_dialog_button)

        addButton.setOnClickListener {
            if(titleTextView.text.trim().isNotEmpty()&& detailsTextView.text.trim().isNotEmpty()){
                addAndroidLesson(titleTextView.text.toString() ,detailsTextView.text.toString())
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    fun editLessonDialog(id: Int,title: String, details: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.edit_dialog)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        //views
        val titleTextView = dialog.findViewById<TextView>(R.id.title_edit_dialog)
        val detailsTextView = dialog.findViewById<TextView>(R.id.details_edit_dialog)
        val saveButton = dialog.findViewById<Button>(R.id.save_edit_button)

        titleTextView.text = title
        detailsTextView.text = details

        saveButton.setOnClickListener {

            val updatedTitle = titleTextView.text.toString()
            val updatedDetails = detailsTextView.text.toString()

            if(updatedTitle.trim().isNotEmpty()&& updatedDetails.trim().isNotEmpty()){
                if(updatedTitle != title || updatedDetails != details){
                    updateAndroidLesson(id,updatedTitle ,updatedDetails)
                }
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    fun deleteDialog(id: Int, title: String, details: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.delete_dialog)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        //views
        val deleteButton = dialog.findViewById<Button>(R.id.delete_delete_button)
        val cancelButton = dialog.findViewById<Button>(R.id.cancel_delete_button)

        cancelButton.setOnClickListener { dialog.cancel() }

        deleteButton.setOnClickListener {
            deleteAndroidLesson(id,title,details)
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}