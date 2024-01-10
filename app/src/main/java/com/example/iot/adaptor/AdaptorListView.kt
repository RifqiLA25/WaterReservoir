package com.example.iot.adaptor

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.iot.BMKG.ListViewActiviry
import com.example.iot.R
import com.example.iot.model.GempaItem
import com.example.iot.model.ModelGempaList

class AdaptorListView(val data: ModelGempaList?, val context: ListViewActiviry, val g: List<GempaItem?>)
    : ArrayAdapter<GempaItem>(context, R.layout.listview, g) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.listview, null, true)

        //indexing
        var _idx = rowView.findViewById<TextView>(R.id.lst_nomor)
        //tanggal
        var _tgl = rowView.findViewById<TextView>(R.id.lst_tanggal)
        //koordinat
        var _koordinat = rowView.findViewById<TextView>(R.id.lst_koordinat)
        //wilayah
        var _wilayah = rowView.findViewById<TextView>(R.id.lst_wilayah)

        //pengisian data
        _idx.setText("#" + (position + 1).toString())
        Log.d("kibem", "posisi: " + (position + 1))

        _tgl.setText(data?.infogempa?.gempa?.get(position)?.tanggal)
        Log.d("kibem", "Tanggal: " + data?.infogempa?.gempa?.get(position)?.tanggal)

        _koordinat.setText(data?.infogempa?.gempa?.get(position)?.coordinates)
        Log.d("kibem", "Koordinat: " + data?.infogempa?.gempa?.get(position)?.coordinates)

        _wilayah.setText(data?.infogempa?.gempa?.get(position)?.wilayah)
        Log.d("kibem", "Wilayah: " + data?.infogempa?.gempa?.get(position)?.wilayah)

        return rowView
        }
}
