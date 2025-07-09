package com.example.islami_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islami_project.databinding.ItemHadethBinding
import com.example.islami_project.api.model.Hadeth
class HadethCarouselAdapter(val hadethList:List<Hadeth>) :RecyclerView.Adapter<HadethCarouselAdapter.ViewHolder>(){
    class ViewHolder(val viewBinding: ItemHadethBinding):RecyclerView.ViewHolder(viewBinding.root){
//
        //
        //        fun bind(hadeth: Hadeth){
//            viewBinding.title.text=hadeth.title
//            viewBinding.contentTv.text=hadeth.content
//        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHadethBinding
            .inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun getItemCount(): Int =hadethList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hadeth=hadethList[position]
        holder.viewBinding.title.text=hadeth.title
        holder.viewBinding.contentTv.text=hadeth.content
        //holder.bind(hadeth)
    }
}