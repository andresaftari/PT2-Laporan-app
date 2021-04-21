package org.ray.nyarioskeun.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.ray.core.data.remote.api.ApiConfig
import org.ray.core.data.remote.api.response.ResponseRegister
import org.ray.nyarioskeun.databinding.ActivityRegisterBinding
import org.ray.nyarioskeun.utils.REGISTER_CHECK

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnRegister.setOnClickListener { GlobalScope.launch { setRegister() } }
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

    private suspend fun setRegister() {
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
        runOnUiThread {
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
                        edtPassword.error =
                            "Password minimal 8 karakter dengan kombinasi alfanumerik!"
                        edtPassword.requestFocus()
                    }
                    pass != confirm -> {
                        edtPassword.error = "Password tidak sama!"
                        edtPassword.requestFocus()
                        edtConfirmPass.error = "Password tidak sama!"
                        edtConfirmPass.requestFocus()
                    }
                    else -> GlobalScope.launch {
                        val nameData = MultipartBody.Part.createFormData("nama", fullname)
                        val passwordData = MultipartBody.Part.createFormData("password", pass)
                        val emailData = MultipartBody.Part.createFormData("email", email)
                        val usernameData = MultipartBody.Part.createFormData("username", username)

                        postRegister(
                            usernameData,
                            passwordData,
                            nameData,
                            emailData,
                            {
                                if (it.status == "success") {
                                    Snackbar.make(
                                        binding.btnRegister,
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
                                    Log.d(
                                        "$REGISTER_CHECK.StatusCheck",
                                        it.msg
                                    )
                                    Snackbar.make(
                                        binding.btnRegister,
                                        "Terjadi kesalahan! ${it.msg}",
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            {
                                Log.d("$REGISTER_CHECK.InputCheck", it)
                                Snackbar.make(
                                    binding.btnRegister,
                                    "Register gagal! $it",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            })
                    }
                }
            }
        }
    }

    private suspend fun postRegister(
        username: MultipartBody.Part,
        password: MultipartBody.Part,
        fullname: MultipartBody.Part,
        email: MultipartBody.Part,
        onSuccess: (ResponseRegister) -> Unit,
        onFailed: (String) -> Unit
    ) = try {
        val response = ApiConfig().service.postRegister(username, password, fullname, email)

        if (response.status == "success") onSuccess(response)
        else onFailed(response.msg)
    } catch (ex: Exception) {
        Log.d(REGISTER_CHECK, "postRegister(): ${ex.message.toString()}")
    }
}