package com.example.tasktimerapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tasktimerapp.R
import com.example.tasktimerapp.databinding.ActivityCategoryBinding
import com.example.tasktimerapp.databinding.ActivityTasksBinding

class TasksActivity : AppCompatActivity() {
    lateinit var binding:ActivityTasksBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}