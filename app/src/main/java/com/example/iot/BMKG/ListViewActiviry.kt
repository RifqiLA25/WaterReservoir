package com.example.iot.BMKG

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.example.iot.R
import com.example.iot.adaptor.AdaptorListView
import com.example.iot.config.dataconfiglist
import com.example.iot.model.ModelGempaList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewActiviry : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_activiry)

        val listview = findViewById<ListView>(R.id.list_gempa)

        dataconfiglist().getService()
            .getDataGempa()
            .enqueue(object : Callback<ModelGempaList>{
                override fun onResponse(
                    call: Call<ModelGempaList>,
                    response: Response<ModelGempaList>
                ) {
                    Log.d("kibem1", "data json : "+ response.body())
                    listview.adapter = AdaptorListView(response.body(), this@ListViewActiviry,
                        response.body()?.infogempa?.gempa!!
                    )

                }

                override fun onFailure(call: Call<ModelGempaList>, t: Throwable) {
                    Log.d("kibem1", "Error : "+ t.message.toString())

                }

            })
    }
}