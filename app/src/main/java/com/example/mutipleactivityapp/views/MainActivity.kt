package com.example.mutipleactivityapp.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mutipleactivityapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startHomePageActivity()
    }

    private fun startHomePageActivity() {
        startActivity(Intent(this@MainActivity, HomePageActivity::class.java))
        finish()
    }
}