package com.example.iot.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iot.R
import com.example.iot.model.ModelClass

class Adapter(private val userList: List<ModelClass>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resource = userList[position].imageview
        val name = userList[position].textview1
        val msg = userList[position].textview2
        val time = userList[position].textview3
        val line = userList[position].divider

        holder.setData(resource, name, msg, time, line)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    // View holder class
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageview)
        private val textView: TextView = itemView.findViewById(R.id.textview1)
        private val textView2: TextView = itemView.findViewById(R.id.textview2)
        private val textview3: TextView = itemView.findViewById(R.id.textview3)
        private val divider: TextView = itemView.findViewById(R.id.Divider)

        fun setData(resource: Int, name: String, msg: String, time: String, line: String) {
            imageView.setImageResource(resource)
            textView.text = name
            textView2.text = msg
            textview3.text = time
            divider.text = line
        }
    }
}
