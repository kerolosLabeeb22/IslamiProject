package com.example.islami_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islami_project.adapter.HadethCarouselAdapter
import com.example.islami_project.constant.MarginItemDecoration
import com.example.islami_project.databinding.FragmentHadithBinding
import com.example.islami_project.api.model.Hadeth
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy


class HadithFragment : Fragment() {

    lateinit var binding: FragmentHadithBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHadithBinding.inflate(inflater,container,false)
        return binding.root
    }

    val hadethList:MutableList<Hadeth> = mutableListOf()
    lateinit var adapter: HadethCarouselAdapter
    lateinit var layoutManager : CarouselLayoutManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readAhadethFile()
        initHadethRecycler()
    }
    private fun initHadethRecycler() {
        adapter =HadethCarouselAdapter(hadethList)
        layoutManager= CarouselLayoutManager(
            FullScreenCarouselStrategy(),
            CarouselLayoutManager.HORIZONTAL
        )
        layoutManager.carouselAlignment=CarouselLayoutManager.ALIGNMENT_CENTER
        val carouselSnapHelper = CarouselSnapHelper()
        carouselSnapHelper.attachToRecyclerView(binding.hadethRecyclerView)
        binding.hadethRecyclerView.adapter=adapter
        binding.hadethRecyclerView.layoutManager=layoutManager
        binding.hadethRecyclerView.addItemDecoration(
            MarginItemDecoration(getMarginInPx())
        )
    }
    fun readAhadethFile(){
        val fileContent= activity?.assets?.open("hadeth/ahadeth.txt")?.
        bufferedReader().use { it?.readText() }
        if (fileContent==null)return
        val hadethLinesList=fileContent.trim()
            .split("#")
        hadethLinesList.forEach{ singleHadeth->
            val lines=singleHadeth.trim()
                .split("\n")
            val title= lines[0]
            val content= lines.takeLast(lines.size-1).joinToString("\n")
            val hadeth = Hadeth(title,content)
            hadethList.add(hadeth)
        }
    }
    fun getMarginInPx(): Int{
        val r = resources
        val px = Math.round(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 24f, r.displayMetrics
            )
        )
        return px
    }

}