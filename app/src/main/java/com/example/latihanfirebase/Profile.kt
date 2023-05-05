package com.example.latihanfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.latihanfirebase.databinding.ActivityMainBinding
import com.example.latihanfirebase.databinding.ActivityProfile2Binding

class Profile : AppCompatActivity() {
    lateinit var binding: ActivityProfile2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfile2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnlogout.setOnClickListener {
            startActivity(Intent(this@Profile, MainActivity::class.java))
        }

    }
}