package org.ray.core.domain.domainModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Account(
    val userid: String? = "",
    val username: String? = "",
    val password: String? = "",
    val email: String? = "",
    val fullname: String? = "",
    val status: String? = ""
) : Parcelable
