package org.ray.nyarioskeun.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.ray.nyarioskeun.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnRegister.setOnClickListener { setRegister() }
            tvToLogin.setOnClickListener {
                startActivity(
                    Intent(
                        this@RegisterActivity,
                        LoginActivity::class.java
                    )
                )
            }
        }
    }

    private fun setRegister() {
        val fullname = binding.edtName.text.toString()
        val username = binding.edtUsername.text.toString()
        val pass = binding.edtPassword.text.toString()
        val confirm = binding.edtConfirmPass.text.toString()

        with(binding) {
            when {
                TextUtils.isEmpty(fullname) -> {
                    edtName.error = "Silakan isi nama lengkap terlebih dahulu"
                    edtName.requestFocus()
                }
                TextUtils.isEmpty(username) -> {
                    edtUsername.error = "Silakan isi username terlebih dahulu"
                    edtUsername.requestFocus()
                }
                TextUtils.isEmpty(pass) -> {
                    edtPassword.error = "Silakan isi password terlebih dahulu"
                    edtPassword.requestFocus()
                }
                TextUtils.isEmpty(confirm) -> {
                    edtConfirmPass.error = "Silakan konfirmasi password"
                    edtConfirmPass.requestFocus()
                }
                else -> {
                    Snackbar.make(
                        binding.btnRegister,
                        "Registrasi berhasil! Silakan login dengan akunmu!",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    val background = object : Thread() {
                        override fun run() {
                            sleep(1000)

                            startActivity(
                                Intent(
                                    this@RegisterActivity,
                                    LoginActivity::class.java
                                )
                            )
                        }
                    }
                    background.start()
                }
            }
        }
    }
}