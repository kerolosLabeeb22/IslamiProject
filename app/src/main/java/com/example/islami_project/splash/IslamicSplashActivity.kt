package com.example.islami_project.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.islami_project.R
import com.example.islami_project.home.HomeActivity

class IslamicSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_islamic_splash)
        Handler(Looper.getMainLooper())
            .postDelayed({
                navigateToHomeScreen()
            }, 2000)

    }

    private fun navigateToHomeScreen(){
        intent=Intent(this@IslamicSplashActivity,HomeActivity::class.java)
        startActivity(intent)
        finish()

    }
}