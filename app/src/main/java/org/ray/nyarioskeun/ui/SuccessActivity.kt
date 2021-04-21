package org.ray.nyarioskeun.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.ray.nyarioskeun.MainActivity
import org.ray.nyarioskeun.databinding.ActivitySuccessBinding

class SuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuccessBinding
    private var fullname = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("EXTRA_ACCOUNT")) {
            fullname = intent.getStringExtra("EXTRA_ACCOUNT").toString()
        }

        object : Thread() {
            override fun run() {
                sleep(2000)

                startActivity(
                    Intent(
                        this@SuccessActivity,
                        MainActivity::class.java
                    ).putExtra("EXTRA_ACCOUNT_RETURN", fullname)
                )
                finish()
            }
        }.start()
    }
}