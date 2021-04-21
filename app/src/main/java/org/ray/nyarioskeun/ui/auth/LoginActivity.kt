package org.ray.nyarioskeun.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.ray.core.data.remote.api.ApiConfig
import org.ray.core.data.remote.api.response.ResponseLogin
import org.ray.nyarioskeun.MainActivity
import org.ray.nyarioskeun.databinding.ActivityLoginBinding
import org.ray.nyarioskeun.utils.LOGIN_CHECK

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
            btnLogin.setOnClickListener { GlobalScope.launch { setLogin() } }
        }
    }

    // Start login
    private suspend fun setLogin() {
        val username = binding.edtLogin.text.toString()
        val pass = binding.edtPass.text.toString()

        runOnUiThread {
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
                    else -> GlobalScope.launch {
                        val name = MultipartBody.Part.createFormData("username", username)
                        val password = MultipartBody.Part.createFormData("password", pass)

                        postLogin(name, password,
                            {
                                if (it.status == "success") {
                                    Snackbar.make(
                                        binding.btnLogin,
                                        "Selamat datang, ${it.user}!",
                                        Snackbar.LENGTH_SHORT
                                    ).show()

                                    Log.d(
                                        "$LOGIN_CHECK.InputCheck",
                                        "$username, $pass, ${it.user}"
                                    )

                                    object : Thread() {
                                        override fun run() {
                                            sleep(1000)

                                            startActivity(
                                                Intent(
                                                    this@LoginActivity,
                                                    MainActivity::class.java
                                                ).apply {
                                                    putExtra("EXTRA_FULLNAME", it.user)
                                                    putExtra("EXTRA_USERNAME", username)
                                                }
                                            )
                                            finish()
                                        }
                                    }.start()
                                } else {
                                    Log.d("$LOGIN_CHECK.StatusCheck", it.msg)
                                    Snackbar.make(
                                        binding.btnLogin,
                                        "Terjadi kesalahan! ${it.msg}",
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            {
                                Log.d("$LOGIN_CHECK.InputCheck", it)
                                Snackbar.make(
                                    binding.btnLogin,
                                    "Login gagal! $it",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            })
                    }
                }
            }
        }
    }

    private suspend fun postLogin(
        username: MultipartBody.Part,
        password: MultipartBody.Part,
        onSuccess: (ResponseLogin) -> Unit,
        onFailed: (String) -> Unit
    ) = try {
        val response = ApiConfig().service.postLogin(username, password)

        if (response.status == "success") onSuccess(response)
        else onFailed(response.msg)
    } catch (ex: Exception) {
        Log.d(LOGIN_CHECK, "postLogin(): ${ex.message.toString()}")
    }
}