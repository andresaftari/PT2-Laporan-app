package org.ray.nyarioskeun.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Account(
    val username: String? = "",
    val password: String? = "",
    val email: String? = "",
    val fullname: String? = "",
    val status: String? = ""
) : Parcelable