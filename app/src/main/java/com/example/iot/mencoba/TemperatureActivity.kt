package com.example.iot.mencoba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import com.example.iot.R

class TemperatureActivity : AppCompatActivity() {

    private lateinit var temperatureProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)

        temperatureProgressBar = findViewById(R.id.temperatureProgressBar)

        // Set orientasi ProgressBar menjadi vertical
        temperatureProgressBar.rotation = 270f

        // Misalkan, suhu aktual diperbarui setiap detik
        val handler = Handler()
        val delay: Long = 1000 // 1 detik

        handler.postDelayed(object : Runnable {
            override fun run() {
                // Dapatkan suhu aktual dari sumber data (misalnya, sensor atau API)
                val currentTemperature = 50

                    // Update nilai progress bar sesuai dengan suhu aktual
                    updateProgressBar(currentTemperature)

                // Atur pemanggilan rekursif untuk memperbarui suhu setiap detik
                handler.postDelayed(this, delay)
            }
        }, delay)
    }

    private fun updateProgressBar(currentTemperature: Int) {
        // Sesuaikan nilai suhu dengan nilai maksimal ProgressBar
        val maxTemperature = 100
        val progressValue = (currentTemperature.toFloat() / maxTemperature.toFloat() * 100).toInt()

        // Update nilai progress bar
        temperatureProgressBar.progress = progressValue
    }
}