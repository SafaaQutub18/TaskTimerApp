package com.example.tasktimerapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tasktimerapp.R
import com.example.tasktimerapp.database.Category
import com.example.tasktimerapp.databinding.ActivityCategoryBinding
import com.example.tasktimerapp.databinding.ActivityMainBinding

class CategoryActivity : AppCompatActivity() {
    lateinit var binding:ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun addAlert(image : Int){

    }
}