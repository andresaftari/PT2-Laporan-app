package org.ray.nyarioskeun.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.ray.nyarioskeun.databinding.ActivityWelcomeBinding
import org.ray.nyarioskeun.ui.auth.LoginActivity
import org.ray.nyarioskeun.ui.auth.RegisterActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnLogin.setOnClickListener {
                startActivity(
                    Intent(
                        this@WelcomeActivity,
                        LoginActivity::class.java
                    )
                )
            }
            btnRegister.setOnClickListener {
                startActivity(
                    Intent(
                        this@WelcomeActivity,
                        RegisterActivity::class.java
                    )
                )
            }
        }
    }
}