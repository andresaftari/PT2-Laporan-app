package org.ray.nyarioskeun.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.ray.nyarioskeun.MainActivity
import org.ray.nyarioskeun.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            tvToRegister.setOnClickListener {
                startActivity(
                    Intent(
                        this@LoginActivity,
                        RegisterActivity::class.java
                    )
                )
            }
            btnIgracias.setOnClickListener {
                Snackbar.make(
                    btnIgracias,
                    "Sabar yaaaa beb, masih dalam pengembangan",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            btnLogin.setOnClickListener { setLogin() }
        }
    }

    private fun setLogin() {
        val username = binding.edtLogin.text.toString()
        val pass = binding.edtPass.text.toString()

        with(binding) {
            when {
                TextUtils.isEmpty(username) -> {
                    edtLogin.error = "Silakan isi username terlebih dahulu"
                    edtLogin.requestFocus()
                }
                TextUtils.isEmpty(pass) -> {
                    edtPass.error = "Silakan isi password terlebih dahulu"
                    edtPass.requestFocus()
                }
                else -> {
                    Snackbar.make(
                        binding.btnLogin,
                        "Selamat datang, $username!",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    val background = object : Thread() {
                        override fun run() {
                            sleep(1000)

                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    MainActivity::class.java
                                ).putExtra("EXTRA_USERNAME", username)
                            )
                        }
                    }
                    background.start()
                }
            }
        }
    }
}