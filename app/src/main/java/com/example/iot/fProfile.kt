package com.example.iot

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iot.BMKG.BMKGActivity
import com.example.iot.BMKG.BMKGActivityList
import com.example.iot.BMKG.ListViewActiviry
import com.example.iot.adaptor.AdapterProfile
import com.example.iot.model.ModelClass

class fProfile : Fragment(), AdapterProfile.OnItemClickListener {

    private lateinit var mrecyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var userList: MutableList<ModelClass>
    private lateinit var adapter: AdapterProfile

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_f_profile, container, false)

        initData()
        initRecyclerView(view)

        return view
    }

    private fun initRecyclerView(view: View) {
        mrecyclerView = view.findViewById(R.id.RecyclerView)
        layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = RecyclerView.VERTICAL
        mrecyclerView.layoutManager = layoutManager
        adapter = AdapterProfile(userList, this)
        mrecyclerView.adapter = adapter
    }

    private fun initData() {
        userList = mutableListOf()

        userList.add(ModelClass(R.drawable.profile1, "Profile", "Name, Email, Phone", ">", "_______________________________________"))
        userList.add(ModelClass(R.drawable.bmkg, "Data BMKG", "Mengambil API Public BMKG", ">", "_______________________________________"))
        userList.add(ModelClass(R.drawable.bmkg, "Data BMKG List", "Mengambil API Public BMKG", ">", "_______________________________________"))
        userList.add(ModelClass(R.drawable.keluar, "Log Out", "Tekan Untuk Keluar", ">", "_______________________________________"))
    }

    override fun onItemClick(position: Int) {
        val selectedItem = userList[position]

        // Check if the selected item contains the ">" text
        if (selectedItem.textview3.contains(">")) {
            // Implement navigation logic based on the item text
            when (selectedItem.textview1) {
                "Profile" -> startActivity(Intent(requireContext(), Main2Activity::class.java))
                "Data BMKG" -> startActivity(Intent(requireContext(), BMKGActivity::class.java))
                "Data BMKG List" -> startActivity(Intent(requireContext(), ListViewActiviry::class.java))
                // Add more cases for other options as needed
                else -> {
                    // Handle unknown case
                }
            }
        }
    }
}
