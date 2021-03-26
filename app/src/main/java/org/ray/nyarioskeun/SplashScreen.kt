package org.ray.nyarioskeun

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.ray.nyarioskeun.ui.WelcomeActivity
import org.ray.nyarioskeun.utils.SPLASH_SCREEN_TAG

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        val background = object : Thread() {
            override fun run() {
                try {
                    sleep(2500)
                    startActivity(Intent(baseContext, WelcomeActivity::class.java))
                } catch (e: Exception) {
                    Log.i(
                        "$SPLASH_SCREEN_TAG.Splash",
                        "Failed! ${e.localizedMessage}"
                    )
                }
            }
        }
        background.start()
    }
}