package com.example.tasktimerapp.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerapp.Activity.CategoryActivity
import com.example.tasktimerapp.R
import com.example.tasktimerapp.database.Category
import com.example.tasktimerapp.databinding.CategoryRecyclerviewBinding

class CategoryAdapter(private val activity: CategoryActivity): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(val binding: CategoryRecyclerviewBinding):RecyclerView.ViewHolder(binding.root)

    private var counterBackground = 0
    private var backgroundList: List<Int> =
        listOf(R.drawable.light_blue_s,
            R.drawable.pink_s,
            R.drawable.purple_s,
            R.drawable.dark_blue_s)

    private var categoryList: List<Category> =  emptyList()

    fun setcaregoryList(notesList: List<Category>) {
        this.categoryList = notesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if(counterBackground >= categoryList.size)
            counterBackground = 0

        val currentCategory = categoryList[position]

        holder.binding.apply {
            //set background of item
            backgLayout.setBackgroundColor(backgroundList[counterBackground])
            counterBackground++

            iconIV.setImageResource(currentCategory.categoryImage)
            catNameTV.text = currentCategory.categoryName

            cardLayout.setOnClickListener {
                activity.goToTasksView(currentCategory.categoryName)
            }
        }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}