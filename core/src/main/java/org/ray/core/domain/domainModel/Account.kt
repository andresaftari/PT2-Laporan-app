package org.ray.core.domain.domainModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Account(
    val username: String? = "",
    val password: String? = "",
    val email: String? = "",
    val fullname: String? = "",
    val status: String? = "",
    var message: String? = "",
    var user: String? = ""
) : Parcelable