package com.example.tasktimerapp.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerapp.Activity.CategoryActivity
import com.example.tasktimerapp.R
import com.example.tasktimerapp.databinding.IconRecyclerviewBinding

class IconAdapter(private val activity: CategoryActivity): RecyclerView.Adapter<IconAdapter.IconViewHolder>() {

    private val icons: List<Int> =
        listOf(R.drawable.book,
        R.drawable.child,
        R.drawable.cooking,
        R.drawable.design_thinking,
        R.drawable.education,
        R.drawable.home,
        R.drawable.shopping_cart,
        R.drawable.suitcase)

    class IconViewHolder(val binding: IconRecyclerviewBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        return IconViewHolder(
            IconRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        val icon = icons[position]

        holder.binding.apply {
            addSectionIcons.setOnClickListener {
               activity.showAddAlert(icon)
            }
        }

    }

    override fun getItemCount(): Int {
        return icons.size
    }
}