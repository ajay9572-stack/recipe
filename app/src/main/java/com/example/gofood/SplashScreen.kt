package com.example.gofood

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.renderscript.ScriptGroup.Binding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.gofood.databinding.SplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private  lateinit var binding: SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       binding = DataBindingUtil.setContentView(this , R.layout.splash_screen)
        with(binding){
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    startActivity(Intent(this@SplashScreen, SignUp::class.java))
                    finish()
                },3000
            )

        }

    }
}