package com.example.iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.iot.databinding.ActivityMain2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Main2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchButton.setOnClickListener {
            val searchPhone : String = binding.searchPhone.text.toString()
            if  (searchPhone.isNotEmpty()){
                readData(searchPhone)
            }else{
                Toast.makeText(this,"PLease enter the phone number", Toast.LENGTH_SHORT).show()
            }
        }

        binding.mainUpload.setOnClickListener(View.OnClickListener{
            val intent = Intent(this@Main2Activity, UploadActivity::class.java)
            startActivity(intent)
            finish()
        })
        binding.mainUpdate.setOnClickListener(View.OnClickListener{
            val intent = Intent(this@Main2Activity, UpdateActivity::class.java)
            startActivity(intent)
        })
        binding.mainDelete.setOnClickListener(View.OnClickListener{
            val intent = Intent(this@Main2Activity, DeleteActivity::class.java)
            startActivity(intent)
        })
    }
    private fun readData(phone: String) {
        database = FirebaseDatabase.getInstance().getReference("User")
        database.child(phone).get().addOnSuccessListener {
            if (it.exists()){
                val name = it.child("name").value
                val email = it.child("email").value
                val alamat = it.child("alamat").value
                Toast.makeText(this,"Results Found",Toast.LENGTH_SHORT).show()
                binding.searchPhone.text.clear()
                binding.readName.text = name.toString()
                binding.readOperator.text = email.toString()
                binding.readLocation.text = alamat.toString()
            }else{
                Toast.makeText(this,"Phone number does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }
}