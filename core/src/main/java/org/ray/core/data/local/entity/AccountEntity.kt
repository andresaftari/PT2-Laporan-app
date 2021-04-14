package org.ray.core.data.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountEntity(
    val username: String? = "",
    val password: String? = "",
    val email: String? = "",
    val fullname: String? = "",
    val status: String? = ""
) : Parcelable