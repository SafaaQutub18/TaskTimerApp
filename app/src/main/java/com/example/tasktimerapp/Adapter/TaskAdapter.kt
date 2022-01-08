package com.example.tasktimerapp.Adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerapp.Activity.TasksActivity
import com.example.tasktimerapp.R
import com.example.tasktimerapp.database.Task
import com.example.tasktimerapp.database.relations.CategoryTasksRelationship
import com.example.tasktimerapp.databinding.TaskRecyclerviewBinding

class TaskAdapter(private val activity: TasksActivity): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    class TaskViewHolder(val binding: TaskRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

    private var counterBackground = 0
    private var backgroundList: List<Int> =
        listOf(
            R.drawable.light_blue_r,
            R.drawable.pink_r,
            R.drawable.purple_r,
        )

    //private var tasks: List<Task> = emptyList()  CategoryTasksRelationship
    private var tasks: List<Task> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            TaskRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

            if (counterBackground >= backgroundList.size)
                counterBackground = 0

        holder.binding.apply {
            //set background of item
            taskLL.setBackgroundResource(backgroundList[counterBackground])
            counterBackground++

                itemTaskTV.text = task.taskTitle
                itemTimerTV.text = "Time ${task.taskTime}"
                descriptionTV.text = task.taskDescription

        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun displayTasks(userTasks: List<Task>) {
        this.tasks = userTasks
        notifyDataSetChanged()
    }
}