package org.ray.nyarioskeun.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.ray.nyarioskeun.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}