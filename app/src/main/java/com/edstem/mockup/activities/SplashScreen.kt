package com.edstem.mockup.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.edstem.mockup.databinding.ActivitySplashBinding
import com.edstem.mockup.hideSystemBars

class SplashScreen: AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        hideSystemBars()
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(
                    this@SplashScreen,
                    MainActivity::class.java
                )
            )
            finish()
        }, 3000)
    }
}