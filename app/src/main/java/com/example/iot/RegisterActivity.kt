package com.example.iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iot.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // val register = findViewById<Button>(R.id.btn_register)
        //register.setOnClickListener {
        //    sendMessage()
        // }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.iHaveAccount.setOnClickListener {
            val intent = Intent(this , LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegister.setOnClickListener {
            val namalengkap = binding.etFullname.text.toString()
            val email = binding.etEmail.text.toString()
            val usrname = binding.etUsername.text.toString()
            val pass = binding.etPassword.text.toString()
            val confirmPass = binding.etConfirmPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty() && namalengkap.isNotEmpty() && usrname.isNotEmpty()){
                if (pass == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this , LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString() , Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "Password is not matching" , Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this , "Empty Fields are not allowed" , Toast.LENGTH_SHORT).show()
            }

        }
    }

    // private fun sendMessage() {
    //    val namalengkap = findViewById<TextView>(R.id.nama2_profile)
    //     val email = findViewById<TextView>(R.id.email2_profile)
    //    val username = findViewById<TextView>(R.id.no2_profile)

    //    val message = namalengkap.text.toString()
    //   val message1 = email.text.toString()
    //   val message2 = username.text.toString()

    // val fragment = fProfile.newInstance(message, message1, message2)

    // supportFragmentManager.beginTransaction().replace(R.id.f_container, fragment).commit()
    //}


}