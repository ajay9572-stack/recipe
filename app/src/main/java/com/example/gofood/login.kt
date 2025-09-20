package com.example.gofood

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.gofood.SignUp.Companion.auth
import com.example.gofood.databinding.ActivityLoginBinding
import com.example.gofood.ui.notifications.HomeScreen

class login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        with(binding) {
           login.setOnClickListener {
           intent = Intent(this@login, HomeScreen::class.java)
               startActivity(intent)
           }
            login.setOnClickListener {
                val email = binding.gamillogin.text.toString()
                val  password = binding.passwordlogin.text.toString()
                if (email.isNotEmpty()&& password.isNotEmpty()){
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                        if (it.isSuccessful){
                            startActivity(Intent(this@login,HomeScreen::class.java))
                            finish()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this@login,it.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
}