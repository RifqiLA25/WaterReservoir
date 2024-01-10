package com.example.iot.BMKG

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.iot.R

class BMKGActivityList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmkglist)

        val listgempa = findViewById<ListView>(R.id.Listgempa)
    }
}