package org.ray.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Account(
    val username: String? = "",
    val fullname: String? = "",
) : Parcelable