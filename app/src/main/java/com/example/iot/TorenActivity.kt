package com.example.iot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.ToggleButton
import com.example.iot.animasi.MyView
import com.example.iot.config.DataConfigWaterR
import com.example.iot.model.Modelwaterreservoir
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TorenActivity : AppCompatActivity() {

    private lateinit var persenTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var progressBar2: ProgressBar
    private lateinit var progressBar3: ProgressBar
    private lateinit var progressBar4: ProgressBar
    private lateinit var celci: TextView
    private lateinit var debit: TextView
    private lateinit var waterlevel: TextView
    private lateinit var status: ToggleButton
    private var handler = Handler()
    private lateinit var myView: MyView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toren)

        persenTextView = findViewById(R.id.persen)
        progressBar = findViewById(R.id.progressBar)
        progressBar2 = findViewById(R.id.progressBar2)
        progressBar3 = findViewById(R.id.progressBar3)
        progressBar4 = findViewById(R.id.progressBar4)
        celci = findViewById(R.id.celci)
        debit = findViewById(R.id.debit)
        waterlevel = findViewById(R.id.wterlevel)
        status = findViewById(R.id.onoff)
        myView = findViewById(R.id.MyView)

        persenTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }// Not needed


            override fun afterTextChanged(s: Editable?) {
                // Pastikan myView sudah diinisialisasi sebelum digunakan
                if (::myView.isInitialized) {
                    // Ketika nilai TextView persen berubah, perbarui ProgressBar
                    val persenValue = s?.toString()?.toIntOrNull() ?: 0
                    progressBar.progress = persenValue
                    myView.setPercentageFromTextView(s ?: "")
                } else {
                    // Lakukan penanganan jika myView belum diinisialisasi
                    Log.e("TorenActivity", "myView belum diinisialisasi.")
                }
            }
        })

        celci.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }

            override fun afterTextChanged(s: Editable?) {
                // Ketika nilai TextView persen berubah, perbarui ProgressBar
                val persenValue = s?.toString()?.toIntOrNull() ?: 0
                progressBar2.progress = persenValue
            }
        })

        debit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }

            override fun afterTextChanged(s: Editable?) {
                // Ketika nilai TextView persen berubah, perbarui ProgressBar
                val persenValue = s?.toString()?.toIntOrNull() ?: 0
                progressBar3.progress = persenValue
            }
        })
        waterlevel.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }

            override fun afterTextChanged(s: Editable?) {
                // Ketika nilai TextView persen berubah, perbarui ProgressBar
                val persenValue = s?.toString()?.toIntOrNull() ?: 0
                progressBar4.progress = persenValue
            }
        })


        handler.postDelayed(object : Runnable {
            override fun run() {
                // Panggil metode getDataWater untuk mendapatkan data dan mengupdate TextView
                getDataWater()
                // Atur handler untuk dijalankan lagi setelah 5 detik
                handler.postDelayed(this, 1000)
            }
        }, 1000)
        setupToggleButton()
    }

    private fun setupToggleButton() {
        status.setOnCheckedChangeListener { _, isChecked ->
            // Mengubah status TextView dan warna ToggleButton sesuai isChecked
            when {
                isChecked -> {
                    status.text = "ON"
                    status.setBackgroundColor(resources.getColor(R.color.greenLight))
                    // Mengirim status ON (1) ke server
                }
                !isChecked -> {
                    status.text = "OFF"
                    status.setBackgroundColor(resources.getColor(R.color.merah))
                    // Mengirim status OFF (0) ke server
                }
                else -> {
                    status.text = "ERROR"
                    status.setBackgroundColor(resources.getColor(R.color.abu))
                    // Mengirim status ERROR (2) ke server
                }
            }
        }
    }


    private fun handlePumpStatus(statusPompa: Int) {
        when (statusPompa) {
            1 -> {
                // Status ON
                status.text = "ON"
                status.setBackgroundColor(resources.getColor(R.color.greenLight))
                status.isChecked = true // Set ToggleButton ke posisi ON
            }
            0 -> {
                // Status OFF
                status.text = "OFF"
                status.setBackgroundColor(resources.getColor(R.color.merah))
                status.isChecked = false // Set ToggleButton ke posisi OFF
            }
            2 -> {
                // Status ERROR
                status.text = "ERROR"
                status.setBackgroundColor(resources.getColor(R.color.abu))
                // Tidak memperbarui ToggleButton karena status tidak jelas
            }
            else -> {
                Log.e("RifqiLuthfiA", "Status pompa tidak dikenali: $statusPompa")
            }
        }
    }


    private fun getDataWater() {
        DataConfigWaterR().getService()
            .getDataWater()
            .enqueue(object : Callback<Modelwaterreservoir> {
                override fun onResponse(
                    call: Call<Modelwaterreservoir>,
                    response: Response<Modelwaterreservoir>
                ) {
                    runOnUiThread {
                        if (response.isSuccessful) {
                            // Mengubah nilai kapasitas dari string ke integer
                            val kapasitasString = response.body()?.kapasitas
                            try {
                                val kapasitasFloat = kapasitasString?.toFloat() ?: 0.0f
                                val kapasitasInt = kapasitasFloat.toInt()
                                persenTextView.text = kapasitasString
                                progressBar.progress = kapasitasInt
                            } catch (e: NumberFormatException) {
                                Log.e("RifqiLuthfiA", "Error converting kapasitas to float or integer: ${e.message}")
                            }

                            val suhuString = response.body()?.suhu
                            try {
                                val suhuFloat = suhuString?.toFloat() ?: 0.0f
                                val suhuInt = suhuFloat.toInt()
                                celci.text = suhuString
                                progressBar2.progress = suhuInt
                            } catch (e: NumberFormatException) {
                                Log.e("RifqiLuthfiA", "Error converting kapasitas to float or integer: ${e.message}")
                            }

                            val debitString = response.body()?.debit
                            try {
                                val debitFloat = debitString?.toFloat() ?: 0.0f
                                val debitInt = debitFloat.toInt()
                                debit.text = debitString
                                progressBar3.progress = debitInt
                            } catch (e: NumberFormatException) {
                                Log.e("RifqiLuthfiA", "Error converting kapasitas to float or integer: ${e.message}")
                            }

                            val waterlevelString = response.body()?.waterLevel
                            try {
                                val waterlevelFloat = waterlevelString?.toFloat() ?: 0.0f
                                val waterlevelInt = waterlevelFloat.toInt()
                                waterlevel.text = waterlevelString
                                progressBar4.progress = waterlevelInt
                            } catch (e: NumberFormatException) {
                                Log.e("RifqiLuthfiA", "Error converting kapasitas to float or integer: ${e.message}")
                            }

                            // Update status ToggleButton berdasarkan respons server
                            handlePumpStatus(response.body()?.statusPompa ?: 0)
                        } else {
                            Log.e("RifqiLuthfiA", "Gagal mendapatkan data. Kode: ${response.code()}")
                        }
                    }
                }

                override fun onFailure(call: Call<Modelwaterreservoir>, t: Throwable) {
                    runOnUiThread {
                        Log.e("RifqiLuthfiA", "Error : ${t.message}")
                    }
                }
            })
    }

}