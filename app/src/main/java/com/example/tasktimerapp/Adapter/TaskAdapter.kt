package com.example.tasktimerapp.Adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerapp.Activity.TasksActivity
import com.example.tasktimerapp.R
import com.example.tasktimerapp.database.Task
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.os.SystemClock
import android.view.View
import com.example.tasktimerapp.databinding.TaskRecyclerviewBinding

class TaskAdapter(private val activity: TasksActivity): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    class TaskViewHolder(val binding: TaskRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    var totalTimee: Long = 0

    private var counterBackground = 0
    private var backgroundList: List<Int> =
        listOf(
            R.drawable.light_blue_s,
            R.drawable.pink_s,
            R.drawable.purple_s,
            R.drawable.dark_blue_s
        )


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

            var runOrNot = 0

            taskCV.setOnClickListener {

                if (expandableLayout.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(taskCV, AutoTransition())
                    expandableLayout.visibility = View.VISIBLE
                    descriptionTV.text = task.taskDescription

                } else {
                    TransitionManager.beginDelayedTransition(taskCV, AutoTransition())
                    expandableLayout.visibility = View.GONE
                }

                startStopBtn.setOnClickListener {
                    if (runOrNot == 0) {
                        startTimer(true)
                        runOrNot = 1
                    } else if (runOrNot == 1) {
                        startTimer(false)

                        task.taskTime = activity.holdTimer
                        itemTimerTV.text = task.taskTime
                        itemTimerTV.text = "Time ${task.taskTime}"

//                        val ttt = itemTimerTV.text.toString()
//                        itemTimerTV.text = activity.binding.bigTimerTV.text.toString()
//
//                        activity.updateTime(position,
//                            itemTaskTV.text.toString(),
//                            descriptionTV.text.toString(),
//                            itemTimerTV.text.toString(),
//                            activity.binding.categoryNameTV.text.toString())

                        notifyDataSetChanged()
                        runOrNot = 0
                    }
                }
            }

            //set background of item
            taskLL.setBackgroundResource(backgroundList[counterBackground])
            counterBackground++

        }
    }


    override fun getItemCount(): Int {
        return tasks.size
    }


    fun displayTasks(userTasks: List<Task>) {
        this.tasks = userTasks
        notifyDataSetChanged()
    }


    private fun startTimer(run: Boolean) {
        var totalTime: Long? = null

        if (run == true) {
            activity.binding.bigTimerTV.base = SystemClock.elapsedRealtime()
            activity.binding.bigTimerTV.start()
        } else {
            activity.binding.bigTimerTV.stop()
            totalTime = SystemClock.elapsedRealtime() - activity.binding.bigTimerTV.base
            totalTimee = totalTime + totalTimee
            activity.holdTimer = activity.binding.bigTimerTV.text.toString()
            val minutes = totalTimee / 1000 / 60
            val seconds = totalTimee / 1000 % 60

            Log.d("123", " $minutes minutes and $seconds seconds")
        }
    }
}
