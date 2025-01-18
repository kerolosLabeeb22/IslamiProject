package com.example.islami_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islami_project.R
import com.example.islami_project.databinding.FragmentQuranBinding


class QuranFragment : Fragment() {
    lateinit var binding: FragmentQuranBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentQuranBinding.inflate(inflater,container,false)
        return binding.root
    }


}