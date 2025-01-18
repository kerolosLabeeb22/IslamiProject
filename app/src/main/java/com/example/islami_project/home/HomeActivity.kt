package com.example.islami_project.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.islami_project.R
import com.example.islami_project.databinding.ActivityHomeBinding
import com.example.islami_project.fragment.HadithFragment
import com.example.islami_project.fragment.QuranFragment
import com.example.islami_project.fragment.RadioFragment
import com.example.islami_project.fragment.SebhaFragment

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.IslamicBottomNavigationView.setOnItemSelectedListener { menuItem->
            if(menuItem.itemId==R.id.navigation_quran){
                showFragment(QuranFragment())
            }
            else if(menuItem.itemId==R.id.navigation_hadith){
                showFragment(HadithFragment())
            }
            else if(menuItem.itemId==R.id.navigation_sebha){
                showFragment(SebhaFragment())
            }
            else if (menuItem.itemId==R.id.navigation_radio){
                showFragment(RadioFragment())
            }

            return@setOnItemSelectedListener true
        }
        binding.IslamicBottomNavigationView.selectedItemId=R.id.navigation_quran
    }

    private fun showFragment(fragment:Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.islamic_fragment_container,fragment)
            .commit()
    }

}