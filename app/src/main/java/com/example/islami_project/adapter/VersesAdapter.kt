package com.example.islami_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islami_project.databinding.ItemChapterBinding
import com.example.islami_project.databinding.ItemChapterDetailsBinding

class VersesAdapter( val verses:List<String>):RecyclerView.Adapter<VersesAdapter.ViewHolder>() {

    class ViewHolder(val viewBinding: ItemChapterDetailsBinding) : RecyclerView.ViewHolder(
        viewBinding.root
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemChapterDetailsBinding.inflate(
            LayoutInflater.from(parent.context)
            ,parent,false)

        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int =verses.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBinding.content.text=verses[position]
    }
}