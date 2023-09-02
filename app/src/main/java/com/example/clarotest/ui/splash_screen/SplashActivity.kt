package com.example.clarotest.ui.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.clarotest.R
import com.example.clarotest.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageView

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val gifImageView = findViewById<GifImageView>(R.id.gifImageView)
        val gifDrawable = GifDrawable(resources, R.drawable.giphy)
        gifImageView.setImageDrawable(gifDrawable)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, gifDrawable.duration.toLong())
    }
}