package com.example.tasktimerapp.Activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasktimerapp.Adapter.TaskAdapter
import com.example.tasktimerapp.R
import com.example.tasktimerapp.TaskViewModel
import com.example.tasktimerapp.databinding.ActivityCategoryBinding
import com.example.tasktimerapp.databinding.ActivityTasksBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class TasksActivity : AppCompatActivity() {
    lateinit var binding:ActivityTasksBinding
    lateinit var recyclerAdapter: TaskAdapter
    lateinit var viewModel: TaskViewModel

    private val navigasjonen = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.ic_task -> {
                return@OnNavigationItemSelectedListener true
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

        binding.bottomNav.setOnNavigationItemSelectedListener(navigasjonen)
        //binding.bottomNav.selectedItemId = R.id.

        binding.apply {
            categoryNameTV.text = intent.getStringExtra("catName")
            recyclerAdapter = TaskAdapter(this@TasksActivity)
            tasksRv.adapter = recyclerAdapter
            tasksRv.layoutManager = LinearLayoutManager(this@TasksActivity)
            startBtn.setOnClickListener { showAddTaskAlert() }
        }


        val categName = binding.categoryNameTV.text.toString()
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        viewModel.fetchCategoriesTasks(categName).observe(this, {
            tasks -> recyclerAdapter.displayTasks(tasks)
        })

    }


    private fun showAddTaskAlert(){

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.add_category_alert)

        val addBtn = dialog.findViewById(R.id.addBtn) as Button
        val titleET = dialog.findViewById(R.id.titleET) as EditText
        val descriptionET = dialog.findViewById(R.id.descriptionET) as EditText
        val categoryN = binding.categoryNameTV.text.toString()

        addBtn.setOnClickListener {
            viewModel.addTask(titleET.text.toString(), descriptionET.text.toString(), "00:00", categoryN)
            dialog.dismiss()
        }
        dialog.show()
    }

}