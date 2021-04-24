package org.ray.nyarioskeun.ui.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import okhttp3.MultipartBody
import org.koin.android.viewmodel.ext.android.viewModel
import org.ray.core.utils.Status
import org.ray.nyarioskeun.MainActivity
import org.ray.nyarioskeun.databinding.ActivityLoginBinding
import org.ray.nyarioskeun.utils.LOGIN_CHECK

@SuppressLint("LogNotTimber")
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthViewModel by viewModel()

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
            btnLogin.setOnClickListener { startLogin() }
        }
    }

    private fun startLogin() {
        val username = binding.edtLogin.text.toString()
        val pass = binding.edtPass.text.toString()

        when {
            TextUtils.isEmpty(username) -> {
                with(binding) {
                    edtLogin.error = "Silakan isi username terlebih dahulu"
                    edtLogin.requestFocus()
                }
            }
            TextUtils.isEmpty(pass) -> {
                with(binding) {
                    edtPass.error = "Silakan isi password terlebih dahulu"
                    edtPass.requestFocus()
                }
            }
            else -> {
                val name = MultipartBody.Part.createFormData("username", username)
                val password = MultipartBody.Part.createFormData("password", pass)

                viewModel.setLogin(name, password).observe(this, {
                    when (it.status) {
                        Status.SUCCESS -> it.data?.let { response ->
                            if (response.status == "success") {
                                Snackbar.make(
                                    binding.root,
                                    "Selamat datang, ${response.user}!",
                                    Snackbar.LENGTH_SHORT
                                ).show()

                                Log.d(
                                    "$LOGIN_CHECK.InputCheck",
                                    "$username, $pass, ${response.user}"
                                )

                                object : Thread() {
                                    override fun run() {
                                        sleep(1000)

                                        startActivity(
                                            Intent(
                                                this@LoginActivity,
                                                MainActivity::class.java
                                            ).apply {
                                                putExtra("EXTRA_FULLNAME", response.user)
                                                putExtra("EXTRA_USERNAME", username)
                                            }
                                        )
                                        finish()
                                    }
                                }.start()
                            } else {
                                Log.d("$LOGIN_CHECK.StatusCheck", response.msg)
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
                            "Login gagal! ${it.message}",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }
    }
}