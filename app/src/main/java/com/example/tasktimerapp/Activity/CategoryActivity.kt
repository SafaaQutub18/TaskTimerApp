package com.example.tasktimerapp.Activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasktimerapp.Adapter.CategoryAdapter
import com.example.tasktimerapp.Adapter.IconAdapter
import com.example.tasktimerapp.R
import com.example.tasktimerapp.TaskViewModel
import com.example.tasktimerapp.database.Category
import com.example.tasktimerapp.databinding.ActivityCategoryBinding
import com.example.tasktimerapp.databinding.ActivityMainBinding

class CategoryActivity : AppCompatActivity() {
    lateinit var binding:ActivityCategoryBinding
    lateinit var iconAdapter : IconAdapter
    lateinit var categoryAdapter : CategoryAdapter
    lateinit var viewModel: TaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            // icon Adapter setting
            iconAdapter = IconAdapter(this@CategoryActivity) // (this)
            //iconRv.adapter = iconAdapter()
            iconRv.layoutManager = LinearLayoutManager(this@CategoryActivity, LinearLayoutManager.HORIZONTAL, false)

            // category Adapter setting
            categoryAdapter = CategoryAdapter(this@CategoryActivity)
            categoryRv.layoutManager = GridLayoutManager(this@CategoryActivity,2)

        }

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        viewModel.fetchCategories().observe(this, {
            categories -> categoryAdapter.setCategoryList(categories)
        })
    }

    fun showAddAlert(image : Int){

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.add_category_alert)

        val addBtn = dialog.findViewById(R.id.addBtn) as Button
        val catNameET = dialog.findViewById(R.id.catNameET) as EditText

        addBtn.setOnClickListener {
            viewModel.addCategory(catNameET.text.toString(), image)
        }
        dialog.show()
    }

    fun goToTasksView(catName : String){
        intent = Intent(applicationContext, TasksActivity::class.java)
        intent.putExtra("catName", catName)
        startActivity(intent)
    }
}