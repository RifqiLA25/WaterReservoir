package com.example.iot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class CreateActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etnama: EditText
    private lateinit var etpass: EditText
    private lateinit var buttonsave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        etnama = findViewById(R.id.nm)
        etpass = findViewById(R.id.pass)
        buttonsave = findViewById(R.id.btn_create)

        buttonsave.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        saveData()
    }

    private fun saveData() {
        val nama = etnama.text.toString().trim()
        val password = etpass.text.toString().trim()

        Log.d("SaveData", "Nama: $nama, Password: $password")

        if (nama.isEmpty()) {
            etnama.error = "isi Nama"
            return
        }
        if (password.isEmpty()) {
            etpass.error = "isi password"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("Client")

        // Check if CId is not null
        val CId = ref.push().key ?: run {
            Toast.makeText(applicationContext, "Failed to generate unique key", Toast.LENGTH_SHORT)
                .show()
            return
        }

        Log.d("SaveData", "Generated Key: $CId")

        val clt = Client(CId, nama, password)

        ref.child(CId).setValue(clt).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, "Gagal menambahkan data", Toast.LENGTH_SHORT)
                    .show()
                // Log error message
                Log.e("SaveData", "Error: ${task.exception?.message}")
            }
        }
    }
}
