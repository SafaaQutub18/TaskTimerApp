package com.example.tasktimerapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tasktimerapp.R
import com.example.tasktimerapp.databinding.ActivityCategoryBinding
import com.example.tasktimerapp.databinding.ActivityTasksBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class TasksActivity : AppCompatActivity() {
    lateinit var binding:ActivityTasksBinding
    private val navigasjonen = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.ic_task -> {
                return@OnNavigationItemSelectedListener false
            }
            R.id.ic_house-> {
                val intent = Intent(this@TasksActivity, MainActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.ic_section -> {
                val intent = Intent(this@TasksActivity, CategoryActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }

        }
        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigation.setOnNavigationItemSelectedListener(navigasjonen)

    }
}