package org.ray.nyarioskeun.ui.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.google.android.material.snackbar.Snackbar
import okhttp3.MultipartBody
import org.koin.android.viewmodel.ext.android.viewModel
import org.ray.core.utils.Status
import org.ray.nyarioskeun.databinding.ActivityRegisterBinding
import org.ray.nyarioskeun.utils.REGISTER_CHECK

@SuppressLint("LogNotTimber")
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnRegister.setOnClickListener { checkRegister() }
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

    // Login Input Check
    private fun checkRegister() {
        val fullname = binding.edtName.text.toString()
        val username = binding.edtUsername.text.toString()
        val pass = binding.edtPassword.text.toString()
        val confirm = binding.edtConfirmPass.text.toString()
        val email = binding.edtEmail.text.toString()

        val isNotEmail = (!email.contains(".ac.id")
                || !email.contains(".com")
                || !email.contains(".co.id"))

        when {
            TextUtils.isEmpty(fullname) -> {
                with(binding) {
                    edtName.error = "Silakan isi fullname terlebih dahulu"
                    edtName.requestFocus()
                }
            }
            TextUtils.isEmpty(username) -> {
                with(binding) {
                    edtUsername.error = "Silakan isi username terlebih dahulu"
                    edtUsername.requestFocus()
                }
            }
            TextUtils.isEmpty(email) -> {
                with(binding) {
                    edtEmail.error = "Silakan isi email terlebih dahulu!"
                    edtEmail.requestFocus()
                }
            }
            !email.contains("@") && isNotEmail -> {
                with(binding) {
                    edtEmail.error = "Silakan isi format email dengan benar!"
                    edtEmail.requestFocus()
                }
            }
            TextUtils.isEmpty(pass) -> {
                with(binding) {
                    edtPassword.error = "Silakan isi password terlebih dahulu!"
                    edtPassword.requestFocus()
                }
            }
            TextUtils.isEmpty(confirm) -> {
                with(binding) {
                    edtConfirmPass.error = "Silakan konfirmasi password!"
                    edtConfirmPass.requestFocus()
                }
            }
            pass.length < 8 || pass.isDigitsOnly() -> {
                with(binding) {
                    edtPassword.error =
                        "Password minimal 8 karakter dengan kombinasi alfanumerik!"
                    edtPassword.requestFocus()
                }
            }
            pass != confirm -> {
                with(binding) {
                    edtPassword.error = "Password tidak sama!"
                    edtPassword.requestFocus()
                    edtConfirmPass.error = "Password tidak sama!"
                    edtConfirmPass.requestFocus()
                }
            }
            else -> startRegister(fullname, username, pass, confirm, email)
        }
    }

    // Start Login
    private fun startRegister(
        fullname: String,
        username: String,
        pass: String,
        confirm: String,
        email: String
    ) {
        val nameData = MultipartBody.Part.createFormData("nama", fullname)
        val passwordData = MultipartBody.Part.createFormData("password", pass)
        val emailData = MultipartBody.Part.createFormData("email", email)
        val usernameData = MultipartBody.Part.createFormData("username", username)

        viewModel.setRegister(
            nameData,
            usernameData,
            passwordData,
            emailData
        ).observe(this, {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { response ->
                    if (response.status == "success") {
                        Snackbar.make(
                            binding.root,
                            "Registrasi berhasil! Silakan login dengan akunmu!",
                            Snackbar.LENGTH_SHORT
                        ).show()

                        Log.d(
                            "$REGISTER_CHECK.InputCheck",
                            "$fullname, $username, $pass, $confirm"
                        )

                        object : Thread() {
                            override fun run() {
                                sleep(1000)

                                startActivity(
                                    Intent(
                                        this@RegisterActivity,
                                        LoginActivity::class.java
                                    )
                                )
                            }
                        }.start()
                    } else {
                        Log.d("$REGISTER_CHECK.StatusCheck", response.msg)
                        Snackbar.make(
                            binding.root,
                            "Terjadi kesalahan! ${response.msg}",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
                Status.LOADING -> Snackbar.make(
                    binding.root,
                    "Mohon menunggu",
                    Snackbar.LENGTH_SHORT
                ).show()
                Status.ERROR -> Snackbar.make(
                    binding.root,
                    "Gagal mendaftar! ${it.message}",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })
    }
}