package org.ray.core.data.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuEntity(
    val thumbnail: Int,
    val name: String
) : Parcelable