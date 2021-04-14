package org.ray.core.domain.domainModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Report(
    val fullname: String? = "",
    val status: Int? = 0,
    val damage: String? = "",
    val location: String? = "",
    val evidenceURL: String? = "",
    val description: String? = ""
) : Parcelable
