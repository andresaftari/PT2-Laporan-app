package org.ray.nyarioskeun.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.ray.nyarioskeun.MainActivity
import org.ray.nyarioskeun.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnLogin.setOnClickListener {
                Toast.makeText(
                    this@WelcomeActivity,
                    "Sabar yaaaa beb, masih dalam pengembangan",
                    Toast.LENGTH_LONG
                ).show()

                Thread.sleep(500)

                startActivity(
                    Intent(
                        this@WelcomeActivity,
                        MainActivity::class.java
                    )
                )
            }
            btnRegister.setOnClickListener {
                Toast.makeText(
                    this@WelcomeActivity,
                    "Sabar yaaaa beb, masih dalam pengembangan",
                    Toast.LENGTH_LONG
                ).show()

                Thread.sleep(500)

                startActivity(
                    Intent(
                        this@WelcomeActivity,
                        MainActivity::class.java
                    )
                )
            }
        }
    }
}