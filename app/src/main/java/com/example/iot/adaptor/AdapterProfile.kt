package com.example.iot.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.iot.R
import com.example.iot.model.ModelClass

class AdapterProfile(private val userList: List<ModelClass>, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<AdapterProfile.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelClass: ModelClass = userList[position]

        holder.imageView.setImageResource(modelClass.imageview)
        holder.title.text = modelClass.textview1
        holder.subtitle.text = modelClass.textview2
        holder.action.text = modelClass.textview3  // Ganti dengan textview3
        holder.divider.text = modelClass.divider

        holder.action.setOnClickListener {
            // Handle click event for textview3
            onItemClickListener.onItemClick(position)
        }
    }


    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val title: TextView = itemView.findViewById(R.id.textview1)
        val subtitle: TextView = itemView.findViewById(R.id.textview2)
        val action: TextView = itemView.findViewById(R.id.textview3)
        val divider: TextView = itemView.findViewById(R.id.Divider)
    }

    }



