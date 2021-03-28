package org.ray.nyarioskeun

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.ray.nyarioskeun.databinding.SplashScreenBinding
import org.ray.nyarioskeun.ui.WelcomeActivity
import org.ray.nyarioskeun.utils.SPLASH_SCREEN_TAG

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val background = object : Thread() {
            override fun run() {
                try {
                    sleep(6000)
                    startActivity(Intent(baseContext, WelcomeActivity::class.java))
                    finish()
                } catch (e: Exception) {
                    Log.i(
                        "$SPLASH_SCREEN_TAG.Process",
                        "Failed! ${e.localizedMessage}"
                    )
                }
            }
        }
        background.start()
    }
}