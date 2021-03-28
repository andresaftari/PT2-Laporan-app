package org.ray.nyarioskeun.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.google.android.material.snackbar.Snackbar
import org.ray.nyarioskeun.databinding.ActivityRegisterBinding
import org.ray.nyarioskeun.utils.REGISTER_CHECK

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
        val email = binding.edtEmail.text.toString()

        val isNotEmail = (
                !email.contains(".ac.id") ||
                        !email.contains(".com") ||
                        !email.contains(".co.id")
                )

        with(binding) {
            when {
                TextUtils.isEmpty(fullname) -> {
                    edtName.error = "Silakan isi nama lengkap terlebih dahulu!"
                    edtName.requestFocus()
                }
                TextUtils.isEmpty(username) -> {
                    edtUsername.error = "Silakan isi username terlebih dahulu!"
                    edtUsername.requestFocus()
                }
                TextUtils.isEmpty(pass) -> {
                    edtPassword.error = "Silakan isi password terlebih dahulu!"
                    edtPassword.requestFocus()
                }
                TextUtils.isEmpty(email) -> {
                    edtEmail.error = "Silakan isi email terlebih dahulu!"
                    edtEmail.requestFocus()
                }
                TextUtils.isEmpty(confirm) -> {
                    edtConfirmPass.error = "Silakan konfirmasi password!"
                    edtConfirmPass.requestFocus()
                }
                !email.contains("@") && isNotEmail -> {
                    edtEmail.error = "Silakan isi format email dengan benar!"
                    edtEmail.requestFocus()
                }
                pass.length < 8 || pass.isDigitsOnly() -> {
                    edtPassword.error = "Password minimal 8 karakter dengan kombinasi alfanumerik!"
                    edtPassword.requestFocus()
                }
                pass != confirm -> {
                    edtPassword.error = "Password tidak sama!"
                    edtPassword.requestFocus()
                    edtConfirmPass.error = "Password tidak sama!"
                    edtConfirmPass.requestFocus()
                }
                else -> {
                    Snackbar.make(
                        binding.btnRegister,
                        "Registrasi berhasil! Silakan login dengan akunmu!",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    Log.d(
                        "$REGISTER_CHECK.InputCheck",
                        "$fullname, $username, $pass, $confirm"
                    )

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