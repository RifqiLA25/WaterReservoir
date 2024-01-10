package com.example.iot.BMKG

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.iot.R
import com.example.iot.config.dataconfig
import com.example.iot.model.Modelgempa
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BMKGActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmkgactivity)

        var tgl = findViewById<TextView>(R.id.tv_tanggal)
        var jm = findViewById<TextView>(R.id.tv_jam)
        var dtm = findViewById<TextView>(R.id.tv_datetime)
        var koor = findViewById<TextView>(R.id.tv_koordinator)
        var lin = findViewById<TextView>(R.id.tv_lintang)
        var buj = findViewById<TextView>(R.id.tv_bujur)
        var mgn = findViewById<TextView>(R.id.tv_magnitude)
        var kdl = findViewById<TextView>(R.id.tv_kedalaman)
        var wly = findViewById<TextView>(R.id.tv_wilayah)
        var pts = findViewById<TextView>(R.id.tv_potensi)
        var drs = findViewById<TextView>(R.id.tv_dirasakan)
        var map = findViewById<ImageView>(R.id.shake_map)

        dataconfig().getService()
            .getDataGempa()
            .enqueue(object : Callback<Modelgempa>{
                override fun onResponse(call: Call<Modelgempa>, response: Response<Modelgempa>) {
                    Log.d("kibem1", "data json : "+ response.body())

                    //parsing
                    tgl.setText(response.body()?.infogempa?.gempa?.tanggal)
                    jm.setText(response.body()?.infogempa?.gempa?.jam)
                    dtm.setText(response.body()?.infogempa?.gempa?.dateTime)
                    koor.setText(response.body()?.infogempa?.gempa?.coordinates)
                    lin.setText(response.body()?.infogempa?.gempa?.lintang)
                    buj.setText(response.body()?.infogempa?.gempa?.bujur)
                    mgn.setText(response.body()?.infogempa?.gempa?.magnitude)
                    kdl.setText(response.body()?.infogempa?.gempa?.kedalaman)
                    wly.setText(response.body()?.infogempa?.gempa?.wilayah)
                    pts.setText(response.body()?.infogempa?.gempa?.potensi)
                    drs.setText(response.body()?.infogempa?.gempa?.dirasakan)

                    Picasso.get()
                        .load("https://data.bmkg.go.id/DataMKG/TEWS/" + response.body()?.infogempa?.gempa?.shakemap)
                        .into(map)

                }

                override fun onFailure(call: Call<Modelgempa>, t: Throwable) {
                    Log.d("kibem1", "Error : "+ t.message.toString())

                }

            })
    }
}