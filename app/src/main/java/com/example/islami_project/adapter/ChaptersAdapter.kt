package com.example.islami_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islami_project.databinding.ItemChapterBinding
import com.example.islami_project.model.Chapter
class ChaptersAdapter(val chapters: List<Chapter>): RecyclerView.Adapter<ChaptersAdapter.ViewHolder>() {
    class ViewHolder(val itemBinding: ItemChapterBinding)
        : RecyclerView.ViewHolder(itemBinding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =ItemChapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(itemBinding)
    }
    override fun getItemCount(): Int =chapters.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chapter =chapters[position]
        holder.itemBinding.arabicTitleTv.text=chapter.titleAr
        holder.itemBinding.englishTitleTv.text=chapter.titleEn
        holder.itemBinding.chapterIndexTv.text="${chapter.index+1}"
        holder.itemBinding.versesNumberTv.text=chapter.versesNumber
        onItemClicks?.let {onClick->
            holder.itemView.setOnClickListener {
                onClick.onItemClick(position,chapter)
            }
        }

    }
    var onItemClicks: onItemClick? =null
    fun interface onItemClick{
        fun onItemClick(position: Int,chapter: Chapter)
    }
}