package com.example.islami_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami_project.databinding.FragmentSebhaBinding


class SebhaFragment : Fragment() {

    private val azkar = arrayOf("سبحان الله", "الحمد لله", "الله أكبر")
    private val zikrCounts = arrayOf(34, 34, 34)
    private var currentIndex = 0
    private var currentCount = 0
    lateinit var binding: FragmentSebhaBinding
    private var currentRotation = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSebhaBinding.inflate(inflater,container,false)


        updateZikr()

        binding.sebhaCounter.setOnClickListener {
            currentCount++
            currentRotation += 30f;
            binding.sebhaImv.setRotation(currentRotation);
            //binding.sebhaImv.animate().rotation(currentRotation).setDuration(200);
            binding.sebhaCounter.text = currentCount.toString()


            if (currentCount >= zikrCounts[currentIndex]) {
                currentCount = 0
                currentIndex = (currentIndex + 1) % azkar.size
                updateZikr()
            }
        }

        return binding.root


    }
    private fun updateZikr() {
        binding.sebhaTv.text = azkar[currentIndex]
        binding.sebhaCounter.text = currentCount.toString()
    }

}