package com.example.loginpage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FrameworksAdapter(private val frameworks: List<FrameWorkPhoto>) :
    RecyclerView.Adapter<FrameworksAdapter.FrameworksViewHolder>() {

    class FrameworksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val frameworkIcon: ImageView = itemView.findViewById(R.id.frameworkIcon)
        val frameworkName: TextView = itemView.findViewById(R.id.item_holder)
    }

    @SuppressLint("ResourceType")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrameworksViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return FrameworksViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return frameworks.size
    }

    override fun onBindViewHolder(holder: FrameworksViewHolder, position: Int) {
        val framework = frameworks[position]
        holder.frameworkIcon.setImageResource(framework.iconResId)
        holder.frameworkName.text = framework.name
    }
}