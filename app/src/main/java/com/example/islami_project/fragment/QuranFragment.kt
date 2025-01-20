package com.example.islami_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islami_project.adapter.ChaptersAdapter
import com.example.islami_project.constant.appConstants
import com.example.islami_project.databinding.FragmentQuranBinding


class QuranFragment : Fragment() {
    lateinit var viewBinding: FragmentQuranBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding= FragmentQuranBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    lateinit var adapter:ChaptersAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= ChaptersAdapter(chapters)
        viewBinding.chapterRecycler.adapter=adapter
    }
    val chapters=appConstants.getChapter()

}