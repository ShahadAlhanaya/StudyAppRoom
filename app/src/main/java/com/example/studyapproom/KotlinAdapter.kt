package com.example.studyapproom

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapproom.data.KotlinLesson
import com.example.studyapproom.databinding.ItemRowBinding

class KotlinAdapter(private val activity: KotlinActivity) :
    RecyclerView.Adapter<KotlinAdapter.ItemViewHolder>() {

    private var kotlinLessonsList = emptyList<KotlinLesson>()

    class ItemViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(activity), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            val id = kotlinLessonsList[position].id
            val title = kotlinLessonsList[position].title
            val details = kotlinLessonsList[position].details

            lessonCardView.text = "Lesson ${position+1}"
            titleCardView.text = title
            cardView.setOnClickListener {
                showDetailsDialog(title, details)
            }
            btnEdit.setOnClickListener {
                activity.editLessonDialog(id,title,details)
            }
            btnDelete.setOnClickListener{
                activity.deleteDialog(id,title,details)
            }
        }
    }

    override fun getItemCount() = kotlinLessonsList.size

    fun setData(kotlinLessons: List<KotlinLesson>){
        this.kotlinLessonsList = kotlinLessons
        notifyDataSetChanged()
    }

    private fun showDetailsDialog(title: String, details: String){
        val dialogBuilder = AlertDialog.Builder(activity,R.style.dialog_style)
        val dialogView = LayoutInflater.from(activity).inflate(R.layout.details_dialog,null)
        val  titleView = dialogView.findViewById<TextView>(R.id.title_dialog)
        val descView = dialogView.findViewById<TextView>(R.id.details_dialog)
        titleView.text = title
        descView.text = details
        dialogBuilder.setView(dialogView)
        dialogBuilder.show()

    }
}


