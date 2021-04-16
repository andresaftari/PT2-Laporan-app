package org.ray.core.domain.domainModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Menus(
    val thumbnail: Int,
    val name: String
) : Parcelable